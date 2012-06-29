/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.imapsync;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newFolderBuilder;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import com.sun.mail.imap.IMAPFolder;

import de.bht.fpa.mail.s000000.common.internal.Activator;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.persistence.PersistenceManager;
import de.bht.fpa.mail.s000000.common.rcp.exception.ExceptionDetailsErrorDialog;

/**
 * This class allows to synchronize a remote IMAP-based Mail-account with a
 * local database.
 * 
 * TODO show typical usage of IMAP sync.
 * 
 * @author Siamak Haschemi
 * 
 */
public class ImapHelper {
  private static final String JAVA_MAIL_IMAPS = "imaps";
  private static final String JAVA_MAIL_STORE_PROTOCOL = "mail.store.protocol";

  private static final EntityManager entityManager = PersistenceManager.getInstance().createEntityManagerFactory()
      .createEntityManager();
  private static boolean debug = false;

  private ImapHelper() {
  }

  public static void setDebug(boolean debug) {
    ImapHelper.debug = debug;
  }

  /**
   * Tries to find the account by name.
   * 
   * @param name
   *          the name of the account
   * @return an instance of {@link Account} from the database, or
   *         <code>null</code> if {@link Account} is not stored.
   */
  public static Account getAccount(String name) {
    Query allAccountQuery = entityManager.createQuery("select a from Account a where a.name = :name").setParameter(
        "name", name);
    @SuppressWarnings("unchecked")
    List<Account> list = allAccountQuery.getResultList();

    if (list == null || list.size() == 0) {
      return null;
    }
    return list.get(0);
  }

  public static void saveAccount(Account account) {
    entityManager.getTransaction().begin();
    entityManager.persist(account);
    entityManager.getTransaction().commit();
  }

  /**
   * Synchronizes all folders of an IMAP {@link Account} with a database using
   * the {@link EntityManager}. The current implementation is only able to add
   * new messages, but NOT deleted or moved messages.
   * 
   * @param account
   *          the {@link Account} to use to connect
   * @param monitor
   * @throws SynchronizationException
   */
  public static void syncAllFoldersToAccount(Account account, IProgressMonitor monitor) throws SynchronizationException {
    syncAllFoldersToAccount(account, monitor, new Hashtable<String, String>(), JAVA_MAIL_IMAPS);
  }

  /**
   * Synchronizes all folders of an IMAP {@link Account} with a database using
   * the {@link EntityManager}. The current implementation is only able to add
   * new messages, but NOT deleted or moved messages.
   * 
   * @param account
   *          the {@link Account} to use to connect
   * @param debug
   *          enable/disable IMAP debugging
   * @param protocol
   *          the protocol to create the {@link Store} from. See
   *          {@link Session#getStore(String)}
   * @param properties
   *          a key-value Map to override properties (i.e. for another port).
   * @throws SynchronizationException
   */
  public static void syncAllFoldersToAccount(final Account account, IProgressMonitor monitor,
      Dictionary<String, String> properties, String protocol) throws SynchronizationException {
    try {
      Properties props = combineProperties(properties);

      Session session = Session.getInstance(props);
      Store store = session.getStore(protocol);
      try {
        store.connect(account.getHost(), account.getUsername(), account.getPassword());
        debug(store.toString());

        IMAPFolder imapFolder = (IMAPFolder) store.getDefaultFolder();

        int totalNumberOfFolders = caculateTotalNumberOfFolders(imapFolder);
        monitor.beginTask("Syncing " + totalNumberOfFolders + " IMAP Folders", totalNumberOfFolders);

        for (javax.mail.Folder subFolder : imapFolder.list()) {
          if (monitor.isCanceled()) {
            break;
          }

          Folder syncedFolder = syncFolderInternal(account, monitor, (IMAPFolder) subFolder, null);

          if (!account.getFolders().contains(syncedFolder)) {
            account.getFolders().add(syncedFolder);
            syncedFolder.setAccount(account);
          }
        }
        merge(account);
      } finally {
        monitor.done();
        store.close();
      }
    } catch (final Exception e) {
      Display.getDefault().syncExec(new Runnable() {
        @Override
        public void run() {
          Status status = new Status(Status.ERROR, Activator.PLUGIN_ID, "Synchronization of Account '"
              + account.getName() + "' failed", e.getCause());
          ExceptionDetailsErrorDialog.openError(Display.getDefault().getActiveShell(), status.getMessage(), null,
              status);
        }
      });
    }
  }

  private static int caculateTotalNumberOfFolders(IMAPFolder imapFolder) throws MessagingException {
    javax.mail.Folder[] subFolders = imapFolder.list();
    if (subFolders == null || subFolders.length == 0) {
      return 0;
    }

    int temp = subFolders.length;
    for (javax.mail.Folder f : subFolders) {
      temp += caculateTotalNumberOfFolders((IMAPFolder) f);
    }

    return temp;
  }

  private static Properties combineProperties(Dictionary<String, String> properties) {
    Properties props = System.getProperties();
    props.setProperty(JAVA_MAIL_STORE_PROTOCOL, JAVA_MAIL_IMAPS);

    Enumeration<String> keys = properties.keys();
    while (keys.hasMoreElements()) {
      String nextKey = keys.nextElement();
      props.setProperty(nextKey, properties.get(nextKey));
    }
    return props;
  }

  private static Folder syncFolderInternal(Account account, IProgressMonitor monitor, IMAPFolder imapFolder,
      Folder parent) {

    Folder folder = getFolder(account, imapFolder.getName());
    if (parent != null && !parent.getFolders().contains(folder)) {
      parent.getFolders().add(folder);
      debug("Added Folder " + folder.getFullName() + " to parent " + parent.getFullName());
    }

    long fromUID = folder.getLastUID() + 1;
    debug("Sync IMAP-folder " + imapFolder.getFullName() + " with min UID = " + fromUID);

    try {
      imapFolder.open(javax.mail.Folder.READ_ONLY);
      javax.mail.Message[] messages = imapFolder.getMessagesByUID(fromUID, UIDFolder.LASTUID);
      if (messages == null || messages.length == 0) {
        debug("No new messages");
        return folder;
      }

      debug("IMAP-Folder contains " + messages.length + " messages");
      for (javax.mail.Message message : messages) {
        if (monitor.isCanceled()) {
          break;
        }

        try {
          Message convertedMessage = new MessageConverter().convertJavaxMessage(imapFolder, message);
          debug("Converted Message ID: " + convertedMessage.getId() + "to: " + convertedMessage.toShortString());

          if (!folder.getMessages().contains(convertedMessage)) {
            debug("Added new Message to Folder");
            folder.getMessages().add(convertedMessage);
            long uid = imapFolder.getUID(message);
            folder.setLastUID(uid);
          } else {
            debug("Message already contained in Folder");
          }
        } catch (MessageConversionException e) {
          error("Message could not be converted " + e.getMessage());
        }
      }
    } catch (MessagingException me) {
      error("Could not sync folder " + folder.getFullName() + ": " + me.getMessage());
    } finally {
      monitor.worked(1);
      if (imapFolder.isOpen()) {
        try {
          imapFolder.close(false);
        } catch (MessagingException e) {
          error("Could not close connection to IMAP folder" + folder.getFullName() + ": " + e.getMessage());
        }
      }
    }

    try {
      for (javax.mail.Folder subFolder : imapFolder.list()) {
        syncFolderInternal(account, monitor, (IMAPFolder) subFolder, folder);
      }
    } catch (MessagingException e) {
      error("Could not sync sub folders of" + folder.getFullName() + ": " + e.getMessage());
    }

    return folder;
  }

  private static Folder getFolder(Account account, String fullName) {
    Query allFoldersWithFullName = entityManager
        .createQuery("select f from Folder f where f.account.id = :accountId AND f.fullName = :fullName")
        .setParameter("accountId", account.getId()).setParameter("fullName", fullName);
    @SuppressWarnings("unchecked")
    List<Folder> list = allFoldersWithFullName.getResultList();

    if (list == null || list.size() == 0) {
      Folder folder = newFolderBuilder().fullName(fullName).build();
      return folder;
    }
    return list.get(0);
  }

  private static <T> T merge(T t) {
    entityManager.getTransaction().begin();
    T tNew = entityManager.merge(t);
    entityManager.getTransaction().commit();
    return tNew;
  }

  private static void debug(String msg) {
    if (debug) {
      System.out.println(msg);
    }
  }

  private static void error(String msg) {
    System.err.println(msg);
  }

}
