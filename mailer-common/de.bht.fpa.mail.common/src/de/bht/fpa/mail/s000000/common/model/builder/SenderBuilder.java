package de.bht.fpa.mail.s000000.common.model.builder;

import de.bht.fpa.mail.s000000.common.model.Sender;

public class SenderBuilder {
  private Long id;
  private String email;
  private String personal;

  public Sender build() {
    Sender sender = new Sender();
    sender.setId(id);
    sender.setEmail(email);
    sender.setPersonal(personal);
    return sender;
  }

  public SenderBuilder but() {
    // @formatter:off
    return newSenderBuilder()
        .id(id)
        .email(email)
        .personal(personal);
    // @formatter:on
  }

  public static SenderBuilder newSenderBuilder() {
    return new SenderBuilder();
  }

  public SenderBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public SenderBuilder personal(String personal) {
    this.personal = personal;
    return this;
  }

  public SenderBuilder email(String email) {
    this.email = email;
    return this;
  }

}
