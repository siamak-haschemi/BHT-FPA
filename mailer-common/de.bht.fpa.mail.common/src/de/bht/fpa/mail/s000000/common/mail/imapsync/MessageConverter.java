/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.imapsync;

import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newAttachmentBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newMessageBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newRecipientBuilder;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newSenderBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;

import org.apache.commons.codec.binary.Base64;

import com.sun.mail.imap.IMAPFolder;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.RecipientType;
import de.bht.fpa.mail.s000000.common.mail.model.builder.MessageBuilder;

/**
 * This class is responsible for converting Java Mail Message Objects (coming
 * from IMAP or POP3 accounts) to our own {@link Message} objects.
 * 
 * @author Siamak Haschemi
 */
public final class MessageConverter {
  private static final String X_PRIORITY = "X-Priority";
  private static final Pattern X_PRIORITY_VALUE_PATTERN = Pattern.compile("(\\d).*");
  private static final int X_PRIORITY_HIGH_END = 4;
  private static final int X_PRIORITY_HIGH_START = 2;
  private static final Map<RecipientType, javax.mail.Message.RecipientType> recipientType = new HashMap<RecipientType, javax.mail.Message.RecipientType>();

  public MessageConverter() {
    recipientType.put(RecipientType.TO, javax.mail.Message.RecipientType.TO);
    recipientType.put(RecipientType.CC, javax.mail.Message.RecipientType.CC);
    recipientType.put(RecipientType.BCC, javax.mail.Message.RecipientType.BCC);
  }

  /**
   * Converts a javax {@link javax.mail.Message} to a {@link Message}.
   * 
   * @param uidFolder
   *          the {@link UIDFolder} (i.e. the {@link IMAPFolder} to obtain the
   *          UID of the message from
   * @param javaMailMessage
   *          the {@link javax.mail.Message} to convert
   * @return the converted {@link Message}, or <code>null</code> if conversion
   *         failed.
   * @throws MessageConversionException
   */
  public Message convertJavaxMessage(UIDFolder uidFolder, javax.mail.Message javaMailMessage)
      throws MessageConversionException {
    try {
      // @formatter:off
      MessageBuilder messageBuilder = newMessageBuilder();
  
      messageBuilder
        .id(convertId(uidFolder, javaMailMessage))
        .subject(convertSubject(javaMailMessage))
        .received(convertReceived(javaMailMessage))
        .sent(convertSent(javaMailMessage))
        .read(convertRead(javaMailMessage))
        .importance(convertImportance(javaMailMessage));
      
      convertContent(javaMailMessage.getContent(), messageBuilder);
      convertRecipients(javaMailMessage, messageBuilder);
      convertSender(javaMailMessage, messageBuilder);
  
      return messageBuilder.build();
      // @formatter:on
    } catch (Exception e) {
      throw new MessageConversionException("Could not convert message " + javaMailMessage, e);
    }
  }

  private static long convertId(UIDFolder uidFolder, javax.mail.Message javaMailMessage) throws MessagingException {
    return uidFolder.getUID(javaMailMessage);
  }

  private static String convertSubject(javax.mail.Message javaMailMessage) throws MessagingException {
    return javaMailMessage.getSubject();
  }

  private static Date convertReceived(javax.mail.Message javaMailMessage) throws MessagingException {
    return javaMailMessage.getReceivedDate();
  }

  private static Date convertSent(javax.mail.Message javaMailMessage) throws MessagingException {
    return javaMailMessage.getSentDate();
  }

  private static boolean convertRead(javax.mail.Message javaMailMessage) throws MessagingException {
    return javaMailMessage.isSet(Flags.Flag.SEEN);
  }

  private static void convertSender(javax.mail.Message javaMailMessage, MessageBuilder messageBuilder)
      throws MessagingException {
    Address[] from = javaMailMessage.getFrom();
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

  private static void convertRecipients(javax.mail.Message javaMailMessage, MessageBuilder messageBuilder)
      throws MessagingException {
    for (RecipientType type : RecipientType.values()) {
      addRecipients(messageBuilder, type, javaMailMessage.getRecipients(recipientType.get(type)));
    }
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

  private static Importance convertImportance(javax.mail.Message javaMailMessage) throws MessagingException {
    Importance handleImportanceBasedOnXPriority = handleImportanceBasedOnXPriority(javaMailMessage);
    if (handleImportanceBasedOnXPriority != null) {
      return handleImportanceBasedOnXPriority;
    }

    Importance handleImportanceBasedOnXMSMailPriority = handleImportanceBasedOnXMSMailPriority(javaMailMessage);
    if (handleImportanceBasedOnXMSMailPriority != null) {
      return handleImportanceBasedOnXMSMailPriority;
    }

    Importance handleImportance = handleImportance(javaMailMessage);
    if (handleImportance != null) {
      return handleImportance;
    }

    return Importance.NORMAL;
  }

  private static Importance handleImportance(javax.mail.Message javaMailMessage) throws MessagingException {
    String[] header = javaMailMessage.getHeader("Importance");
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

  private static Importance handleImportanceBasedOnXMSMailPriority(javax.mail.Message javaMailMessage)
      throws MessagingException {
    String[] header = javaMailMessage.getHeader("X-MSMail-Priority");
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

  private static Importance handleImportanceBasedOnXPriority(javax.mail.Message javaMailMessage)
      throws MessagingException {
    String[] header = javaMailMessage.getHeader(X_PRIORITY);
    if (header == null) {
      return null;
    }

    for (String entry : header) {
      Matcher matcher = X_PRIORITY_VALUE_PATTERN.matcher(entry);
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
