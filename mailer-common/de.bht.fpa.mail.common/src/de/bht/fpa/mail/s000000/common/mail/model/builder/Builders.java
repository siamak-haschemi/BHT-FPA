/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model.builder;

//@formatter:off
/**
* This class provides a facade to all builders in this package.
* 
* @author Siamak Haschemi
* 
*/
//@formatter:on
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
