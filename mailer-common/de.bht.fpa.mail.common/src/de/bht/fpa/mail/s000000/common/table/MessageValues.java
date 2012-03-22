package de.bht.fpa.mail.s000000.common.table;

import de.bht.fpa.mail.s000000.common.model.Message;
import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.properties.IValue;

public final class MessageValues {
  private MessageValues() {

  }

  public static final IValue TEXT = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getText();
    }
  };

  public static final IValue SUBJECT = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getSubject();
    }
  };

  public static final IValue SENDER = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getSender();
    }
  };

  public static final IValue IMPORTANCE = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getImportance();
    }
  };

  public static final IValue RECEIVED = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getReceived();
    }
  };

  public static final IValue SENT = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getSent();
    }
  };

  public static final IValue RECIPIENT = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getRecipient();
    }
  };

  public static final IValue ATTACHMENT = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.getAttachment();
    }
  };

  public static final IValue READ = new BaseValue<Message>() {
    @Override
    public Object get(Message message) {
      return message.isRead();
    }
  };
}
