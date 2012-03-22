/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model.builder;

import de.bht.fpa.mail.s000000.common.mail.model.Attachment;

// @formatter:off
/**
 * This class implements the builder design pattern and provides a fluent API.
 * You can create Attachments like this:
 * 
 * <pre>
 * Attachment attachment = AttachmentBuilder.newAttachmentBuilder()
 *   .id(4711L)
 *   .fileName("file.txt")
 *   .body("324dfsdDFSDF")
 * .build();
 * </pre>
 * 
 * @author Siamak Haschemi
 * 
 */
// @formatter:on
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
