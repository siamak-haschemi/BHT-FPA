/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

//@formatter:off
/**
* This class implements the builder design pattern and provides a fluent API.
* You can create Folders like this:
* 
* <pre>
* Folter folder = FolderBuilder.newFolderBuilder()
*   .id(4711L)
*   .fullName("myfolder")
*   .message(MessageBuilder.newMessageBuilder()
*     .text("my email")
*   )
*   .folder(FolderBuilder.newFolderBuilder()
*     .fullName("mysubfolder")
*   )
* .build();
* </pre>
* 
* @author Siamak Haschemi
* 
*/
//@formatter:on
public final class FolderBuilder {
  private Long id;
  private String fullName;
  private Account account;
  private final List<MessageBuilder> messageBuilders = new LinkedList<MessageBuilder>();
  private final List<FolderBuilder> folderBuilders = new LinkedList<FolderBuilder>();
  private final List<Message> messages = new LinkedList<Message>();

  private FolderBuilder() {

  }

  public static FolderBuilder newFolderBuilder() {
    return new FolderBuilder();
  }

  public Folder build() {
    Folder folder = new Folder();
    folder.setId(id);
    folder.setAccount(account);
    folder.setFullName(fullName);

    List<Folder> folders = new ArrayList<Folder>(folderBuilders.size());
    for (FolderBuilder folderBuilder : folderBuilders) {
      folders.add(folderBuilder.build());
    }
    folder.setFolders(folders);

    List<Message> messages = new ArrayList<Message>(messageBuilders.size());
    for (MessageBuilder messageBuilder : messageBuilders) {
      messages.add(messageBuilder.build());
    }
    messages.addAll(this.messages);
    folder.setMessages(messages);

    return folder;
  }

  public FolderBuilder but() {
    // @formatter:off
    return newFolderBuilder()
        .id(id)
        .account(account)
        .fullName(fullName)
        .messages(messageBuilders)
        .folders(folderBuilders);
    // @formatter:on
  }

  public FolderBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public FolderBuilder account(Account account) {
    this.account = account;
    return this;
  }

  public FolderBuilder fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public FolderBuilder message(MessageBuilder messageBuilder) {
    messageBuilders.add(messageBuilder);
    return this;
  }

  public FolderBuilder messages(Collection<MessageBuilder> messageBuilders) {
    this.messageBuilders.addAll(messageBuilders);
    return this;
  }

  public FolderBuilder builtMessages(Collection<Message> messages) {
    this.messages.addAll(messages);
    return this;
  }

  public FolderBuilder folder(FolderBuilder folderBuilder) {
    folderBuilders.add(folderBuilder);
    return this;
  }

  public FolderBuilder folders(Collection<FolderBuilder> folderBuilders) {
    this.folderBuilders.addAll(folderBuilders);
    return this;
  }

}
