package de.bht.fpa.mail.s000000.common.model.builder;

import de.bht.fpa.mail.s000000.common.model.Recipient;
import de.bht.fpa.mail.s000000.common.model.RecipientType;

public class RecipientBuilder {
  private Long id;
  private String email;
  private String personal;
  private RecipientType recipientType;

  public Recipient build() {
    Recipient recipient = new Recipient();
    recipient.setId(id);
    recipient.setEmail(email);
    recipient.setPersonal(personal);
    recipient.setType(recipientType);
    return recipient;
  }

  public RecipientBuilder but() {
    // @formatter:off
    return newRecipientBuilder()
        .id(id)
        .email(email)
        .personal(personal)
        .type(recipientType);
    // @formatter:on
  }

  public static RecipientBuilder newRecipientBuilder() {
    return new RecipientBuilder();
  }

  public RecipientBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public RecipientBuilder personal(String personal) {
    this.personal = personal;
    return this;
  }

  public RecipientBuilder email(String email) {
    this.email = email;
    return this;
  }

  public RecipientBuilder type(RecipientType recipientType) {
    this.recipientType = recipientType;
    return this;
  }

}
