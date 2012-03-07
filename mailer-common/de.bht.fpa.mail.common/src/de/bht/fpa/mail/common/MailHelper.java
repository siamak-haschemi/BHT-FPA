package de.bht.fpa.mail.common;

import static de.bht.fpa.mail.common.model.builder.Builders.newAttachmentBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newMessageBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newRecipientBuilder;
import static de.bht.fpa.mail.common.model.builder.Builders.newSenderBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import com.sun.mail.imap.IMAPFolder;

import de.bht.fpa.mail.common.model.Account;
import de.bht.fpa.mail.common.model.Folder;
import de.bht.fpa.mail.common.model.Importance;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.RecipientType;
import de.bht.fpa.mail.common.model.builder.MessageBuilder;

public final class MailHelper {
  private static final int X_PRIORITY_HIGH_END = 4;
  private static final int X_PRIORITY_HIGH_START = 2;

  private MailHelper() {
  }

  /**
   * Tries to find the account by name.
   * 
   * @param em
   *          the {@link EntityManager} for the database connection
   * @param name
   *          the name of the account
   * @return an instance of {@link Account} from the database, or
   *         <code>null</code> if {@link Account} is not stored.
   */
  public static Account getAccount(EntityManager em, String name) {
    Query allAccountQuery = em.createQuery("select a from Account a where a.name = :name").setParameter("name", name);
    @SuppressWarnings("unchecked")
    List<Account> list = allAccountQuery.getResultList();

    if (list == null || list.size() == 0) {
      return null;
    }
    return list.get(0);
  }

  /**
   * Synchronizes all folders of an IMAP {@link Account} with a database using
   * the {@link EntityManager}. The current implementation is only able to add
   * new messages, but NOT delete, messages.
   * 
   * @param account
   *          the {@link Account} to use to connect
   * @param em
   *          the {@link EntityManager} giving access to the database
   * @throws MessagingException
   */
  public static void syncAllFolders(Account account, EntityManager em) throws MessagingException {
    syncAllFolders(account, em, false, new Hashtable<String, String>(), "imaps");
  }

  /**
   * Synchronizes all folders of an IMAP {@link Account} with a database using
   * the {@link EntityManager}. The current implementation is only able to add
   * new messages, but NOT delete, messages.
   * 
   * @param account
   *          the {@link Account} to use to connect
   * @param em
   *          the {@link EntityManager} giving access to the database
   * @param debug
   *          enable/disable IMAP debugging
   * @param protocol
   *          the protocol to create the {@link Store} from. See
   *          {@link Session#getStore(String)}
   * @param properties
   *          a key-value Map to override properties (i.e. for another port).
   * @throws MessagingException
   */
  public static void syncAllFolders(Account account, EntityManager em, boolean debug,
      Dictionary<String, String> properties, String protocol) throws MessagingException {

    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");

    Enumeration<String> keys = properties.keys();
    while (keys.hasMoreElements()) {
      String nextKey = keys.nextElement();
      props.setProperty(nextKey, properties.get(nextKey));
    }

    Session session = Session.getInstance(props);
    session.setDebug(debug);
    Store store = session.getStore(protocol);
    try {
      store.connect(account.getHost(), account.getUsername(), account.getPassword());
      System.out.println(store);

      IMAPFolder imapFolder = (IMAPFolder) store.getDefaultFolder();

      Folder folder = getFolder(account, imapFolder.getFullName(), em);
      for (javax.mail.Folder subFolder : imapFolder.list()) {
        Folder syncedFolder = syncFolderInternal(account, (IMAPFolder) subFolder, folder, em);
        if (!account.getFolders().contains(syncedFolder)) {
          account.getFolders().add(syncedFolder);
          syncedFolder.setAccount(account);
        }
      }
      em.getTransaction().begin();
      account = em.merge(account);
      em.getTransaction().commit();
    } finally {
      store.close();
    }
  }

  private static Folder syncFolderInternal(Account account, IMAPFolder imapFolder, Folder parent, EntityManager em)
      throws MessagingException {

    Folder folder = getFolder(account, imapFolder.getFullName(), em);
    if (!parent.getFolders().contains(folder)) {
      parent.getFolders().add(folder);
      System.out.println("Added Folder " + folder.getFullName() + " to parent " + parent.getFullName());
    }

    long fromUID = folder.getLastUID() + 1;
    System.out.println("Sync IMAP-folder " + imapFolder.getFullName() + " with min UID = " + fromUID);

    try {
      imapFolder.open(javax.mail.Folder.READ_ONLY);
      javax.mail.Message[] messages = imapFolder.getMessagesByUID(fromUID, UIDFolder.LASTUID);
      System.out.println("IMAP-Folder contains " + messages.length + " messages");

      for (javax.mail.Message message : messages) {
        long uid = imapFolder.getUID(message);
        Message convertedMessage = convertJavaxMessage(imapFolder, message);
        System.out.println("Convert Message ID: " + convertedMessage.getId() + " ... ");

        if (!folder.getMessages().contains(convertedMessage)) {
          System.out.println("Added new Message");
          folder.getMessages().add(convertedMessage);
          folder.setLastUID(uid);
        } else {
          System.out.println("Message already contained by Folder");
        }
      }
      em.getTransaction().begin();
      folder = em.merge(folder);
      em.getTransaction().commit();

    } catch (MessagingException me) {
      System.err.println("Could not sync folder " + folder.getFullName() + ": " + me.getMessage());
    } finally {
      if (imapFolder.isOpen()) {
        imapFolder.close(false);
      }
    }

    for (javax.mail.Folder subFolder : imapFolder.list()) {
      syncFolderInternal(account, (IMAPFolder) subFolder, folder, em);
    }

    return folder;
  }

  /**
   * Converts a javax {@link javax.mail.Message} to a {@link Message}.
   * 
   * @param uidFolder
   *          the {@link UIDFolder} (i.e. the {@link IMAPFolder} to obtain the
   *          UID of the message from
   * @param m
   *          the {@link javax.mail.Message} to convert
   * @return the converted {@link Message}, or <code>null</code> if conversion
   *         failed.
   */
  public static Message convertJavaxMessage(UIDFolder uidFolder, javax.mail.Message m) {
    try {
      // @formatter:off
      MessageBuilder messageBuilder = newMessageBuilder();
  
      messageBuilder
        .id(convertId(uidFolder, m))
        .subject(convertSubject(m))
        .received(convertReceived(m))
        .sent(convertSent(m))
        .read(convertRead(m))
        .importance(convertImportance(m));
      
      convertContent(m.getContent(), messageBuilder);
      convertRecipients(m, messageBuilder);
      convertSender(m, messageBuilder);
  
      return messageBuilder.build();
      // @formatter:on
    } catch (MessagingException e) {
      return null;
    } catch (IOException e) {
      return null;
    }
  }

  private static Folder getFolder(Account account, String fullName, EntityManager em) {
    Query allFoldersWithFullName = em
        .createQuery("select f from Folder f where f.account.id = :accountId AND f.fullName = :fullName")
        .setParameter("accountId", account.getId()).setParameter("fullName", fullName);
    @SuppressWarnings("unchecked")
    List<Folder> list = allFoldersWithFullName.getResultList();

    if (list == null || list.size() == 0) {
      Folder folder = new Folder();
      folder.setFullName(fullName);
      folder.setAccount(account);

      em.getTransaction().begin();
      folder = em.merge(folder);
      em.getTransaction().commit();
      return folder;
    }

    return list.get(0);
  }

  private static long convertId(UIDFolder uidFolder, javax.mail.Message m) throws MessagingException {
    return uidFolder.getUID(m);
  }

  private static String convertSubject(javax.mail.Message m) throws MessagingException {
    return m.getSubject();
  }

  private static Date convertReceived(javax.mail.Message m) throws MessagingException {
    return m.getReceivedDate();
  }

  private static Date convertSent(javax.mail.Message m) throws MessagingException {
    return m.getSentDate();
  }

  private static boolean convertRead(javax.mail.Message m) throws MessagingException {
    return m.isSet(Flags.Flag.SEEN);
  }

  private static void convertSender(javax.mail.Message m, MessageBuilder messageBuilder) throws MessagingException {
    Address[] from = m.getFrom();
    if (from == null || from.length <= 0) {
      return;
    }

    Address firstFrom = from[0];
    if (!(firstFrom instanceof InternetAddress)) {
      return;
    }
    InternetAddress internetAddress = (InternetAddress) firstFrom;
    messageBuilder.sender(newSenderBuilder().email(internetAddress.getAddress())
        .personal(internetAddress.getPersonal()));
  }

  private static void convertRecipients(javax.mail.Message m, MessageBuilder messageBuilder) throws MessagingException {
    javax.mail.Message.RecipientType to = javax.mail.Message.RecipientType.TO;
    addRecipients(messageBuilder, convertRecipientType(to), m.getRecipients(to));

    javax.mail.Message.RecipientType cc = javax.mail.Message.RecipientType.CC;
    addRecipients(messageBuilder, convertRecipientType(cc), m.getRecipients(cc));

    javax.mail.Message.RecipientType bcc = javax.mail.Message.RecipientType.BCC;
    addRecipients(messageBuilder, convertRecipientType(bcc), m.getRecipients(bcc));
  }

  private static void addRecipients(MessageBuilder messageBuilder, RecipientType recipientType, Address[] recipients) {
    if (recipients == null) {
      return;
    }
    for (Address address : recipients) {
      if (!(address instanceof InternetAddress)) {
        continue;
      }
      InternetAddress internetAddress = (InternetAddress) address;

      // @formatter:off
      messageBuilder.recipient(
            newRecipientBuilder()
            .email(internetAddress.getAddress())
            .personal(internetAddress.getPersonal())
            .type(recipientType)
      );
      // @formatter:on
    }
  }

  private static RecipientType convertRecipientType(javax.mail.Message.RecipientType recipientType) {
    if (recipientType.equals(javax.mail.Message.RecipientType.TO)) {
      return RecipientType.TO;
    }

    if (recipientType.equals(javax.mail.Message.RecipientType.CC)) {
      return RecipientType.CC;
    }

    if (recipientType.equals(javax.mail.Message.RecipientType.BCC)) {
      return RecipientType.BCC;
    }
    throw new IllegalArgumentException("Unknown recipient type: " + recipientType);
  }

  private static Importance convertImportance(javax.mail.Message m) throws MessagingException {
    Importance handleImportanceBasedOnXPriority = handleImportanceBasedOnXPriority(m);
    if (handleImportanceBasedOnXPriority != null) {
      return handleImportanceBasedOnXPriority;
    }

    Importance handleImportanceBasedOnXMSMailPriority = handleImportanceBasedOnXMSMailPriority(m);
    if (handleImportanceBasedOnXMSMailPriority != null) {
      return handleImportanceBasedOnXMSMailPriority;
    }

    Importance handleImportance = handleImportance(m);
    if (handleImportance != null) {
      return handleImportance;
    }

    return Importance.NORMAL;
  }

  private static Importance handleImportance(javax.mail.Message m) throws MessagingException {
    String[] header = m.getHeader("Importance");
    if (header == null) {
      return null;
    }

    for (String entry : header) {
      if (entry.equals("high")) {
        return Importance.HIGH;
      }
      if (entry.equals("normal")) {
        return Importance.NORMAL;
      }
      if (entry.equals("low")) {
        return Importance.LOW;
      }
    }
    return null;
  }

  private static Importance handleImportanceBasedOnXMSMailPriority(javax.mail.Message m) throws MessagingException {
    String[] header = m.getHeader("X-MSMail-Priority");
    if (header == null) {
      return null;
    }

    for (String entry : header) {
      if (entry.equals("High")) {
        return Importance.HIGH;
      }
      if (entry.equals("Normal")) {
        return Importance.NORMAL;
      }
      if (entry.equals("Low")) {
        return Importance.LOW;
      }
    }
    return null;
  }

  private static Importance handleImportanceBasedOnXPriority(javax.mail.Message m) throws MessagingException {
    String[] header = m.getHeader("X-Priority");
    if (header == null) {
      return null;
    }

    Pattern pattern = Pattern.compile("(\\d).*");

    for (String entry : header) {
      Matcher matcher = pattern.matcher(entry);
      if (!matcher.matches()) {
        continue;
      }

      Integer flag = Integer.valueOf(matcher.group(1));
      if (flag < X_PRIORITY_HIGH_START) {
        return Importance.HIGH;
      }
      if (X_PRIORITY_HIGH_START <= flag && flag <= X_PRIORITY_HIGH_END) {
        return Importance.NORMAL;
      }
      if (flag > X_PRIORITY_HIGH_END) {
        return Importance.LOW;
      }
    }
    return null;
  }

  private static void convertContent(Object content, MessageBuilder messageBuilder) throws MessagingException,
      IOException {
    handleContent(content, null, messageBuilder);
  }

  private static void handleContent(Object content, BodyPart bodyPart, MessageBuilder messageBuilder)
      throws MessagingException, IOException {
    if (content instanceof String) {
      handleText(content, messageBuilder);
    } else if (content instanceof Multipart) {
      handleMultipart((Multipart) content, messageBuilder);
    } else if (content instanceof InputStream) {
      handleInputStream((InputStream) content, bodyPart, messageBuilder);
    }
  }

  private static void handleInputStream(InputStream content, BodyPart bodyPart, MessageBuilder messageBuilder) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      int thisLine;
      while ((thisLine = content.read()) != -1) {
        bos.write(thisLine);
      }
      bos.flush();
      byte[] bytes = bos.toByteArray();
      bos.close();

      String encodeBase64String = new String(Base64.encodeBase64(bytes));
      // @formatter:off
      messageBuilder.attachment(
          newAttachmentBuilder()
          .fileName(bodyPart.getFileName())
          .body(encodeBase64String)
      );
      // @formatter:on
    } catch (Exception e) {
      // ignore
      return;
    }
  }

  private static void handleText(Object content, MessageBuilder messageBuilder) {
    messageBuilder.text((String) content);
  }

  private static void handleMultipart(Multipart content, MessageBuilder messageBuilder) throws MessagingException,
      IOException {
    for (int i = 0; i < content.getCount(); i++) {
      BodyPart bodyPart = content.getBodyPart(i);
      handleContent(bodyPart.getContent(), bodyPart, messageBuilder);
    }
  }
}
