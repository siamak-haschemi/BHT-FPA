package de.bht.fpa.mail.common.model.builder;

import de.bht.fpa.mail.common.model.Attachment;

public class AttachmentBuilder {

  private Long id;
  private String fileName;
  private String body;

  public Attachment build() {
    Attachment attachment = new Attachment();
    attachment.setId(id);
    attachment.setFileName(fileName);
    attachment.setBody(body);
    return attachment;
  }

  public static AttachmentBuilder newAttachmentBuilder() {
    return new AttachmentBuilder();
  }

  public AttachmentBuilder but() {
    // @formatter:off
    return newAttachmentBuilder()
        .id(id)
        .fileName(fileName)
        .body(body);
    // @formatter:on
  }

  public AttachmentBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public AttachmentBuilder fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  public AttachmentBuilder body(String body) {
    this.body = body;
    return this;
  }

}
