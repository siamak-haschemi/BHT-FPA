package de.bht.fpa.mail.s000000.common.model.builder;

public final class Builders {

  private Builders() {

  }

  public static MessageBuilder newMessageBuilder() {
    return MessageBuilder.newMessageBuilder();
  }

  public static RecipientBuilder newRecipientBuilder() {
    return RecipientBuilder.newRecipientBuilder();
  }

  public static SenderBuilder newSenderBuilder() {
    return SenderBuilder.newSenderBuilder();
  }

  public static AttachmentBuilder newAttachmentBuilder() {
    return AttachmentBuilder.newAttachmentBuilder();
  }

  public static FolderBuilder newFolderBuilder() {
    return FolderBuilder.newFolderBuilder();
  }

  public static AccountBuilder newAccountBuilder() {
    return AccountBuilder.newAccountBuilder();
  }
}
