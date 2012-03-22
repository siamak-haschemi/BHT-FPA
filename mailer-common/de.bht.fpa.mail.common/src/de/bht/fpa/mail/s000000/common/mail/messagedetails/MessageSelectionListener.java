/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.messagedetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

/**
 * This class responds to selections of {@link Message} objects in the workspace
 * and updates the UI of the {@link MessageView}.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class MessageSelectionListener implements ISelectionListener {
  private final MessageView messageView;

  public MessageSelectionListener(MessageView messageView) {
    this.messageView = messageView;
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    Message message = SelectionHelper.handleStructuredSelection(selection, Message.class);
    if (message == null) {
      return;
    }
    syncText(message);
    syncRecipient(message);
    syncReceived(message);
    syncSubject(message);
    syncFrom(message);
  }

  private void syncFrom(Message message) {
    StringBuilder sb = new StringBuilder();
    Sender sender = message.getSender();
    sb.append(sender.getEmail());
    if (sender.getPersonal() != null) {
      sb.append("<");
      sb.append(sender.getPersonal());
      sb.append(">");
    }
    this.messageView.getTxtFrom().setText(sb.toString());
  }

  private void syncSubject(Message message) {
    this.messageView.getTxtSubject().setText(message.getSubject());
  }

  private void syncReceived(Message message) {
    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    this.messageView.getTxtReceived().setText(format.format(message.getReceived()));
  }

  private void syncRecipient(Message message) {
    StringBuilder sb = new StringBuilder();
    for (Recipient r : message.getRecipients()) {
      sb.append(r.getEmail());
      if (r.getPersonal() == null) {
        continue;
      }
      sb.append("<");
      sb.append(r.getPersonal());
      sb.append(">");
    }
    this.messageView.getTxtTo().setText(sb.toString());
  }

  private void syncText(Message message) {
    this.messageView.getBrowserText().setText(message.getText());
  }
}