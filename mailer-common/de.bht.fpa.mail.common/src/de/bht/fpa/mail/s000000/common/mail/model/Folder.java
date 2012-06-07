/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents an imap folder. It contains a list of messages, but
 * also a list of {@link Folder}s. This implements the composite pattern.
 * 
 * <p>
 * <i>Note that this class can be used together with JAXB and JPA.</i>
 * </p>
 * 
 * @author Siamak Haschemi
 * 
 */
@Entity
public class Folder extends BaseEntity {
  private static final long serialVersionUID = -7660640539811469762L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fullName;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Message> messages = new LinkedList<Message>();

  @OneToMany(cascade = CascadeType.ALL)
  private List<Folder> folders = new LinkedList<Folder>();

  @ManyToOne
  private Account account;

  private long lastUID;

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getLastUID() {
    return lastUID;
  }

  public void setLastUID(long lastUID) {
    this.lastUID = lastUID;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public void setFolders(List<Folder> folders) {
    this.folders = folders;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Folder: ");
    s.append("id=").append(id).append(" ");
    s.append("fullName=").append(fullName).append(" ");
    s.append("messages=(");
    for (Message m : messages) {
      s.append(m).append(",");
    }
    s.append(")").append(" ");

    s.append("folders=(");
    for (Folder f : folders) {
      s.append(f).append(",");
    }
    s.append(")");
    s.append("]").append(" ");
    return s.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    // object must be Test at this point
    Folder test = (Folder) obj;
    if (getId() != null && test.getId() != null) {
      return getId().equals(test.getId());
    }
    if (getFullName() != null && test.getFullName() != null) {
      return getFullName().equals(test.getFullName());
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    if (getId() != null) {
      return getId().hashCode();
    }

    if (getFullName() != null) {
      return getFullName().hashCode();
    }

    return super.hashCode();
  }

  @Override
  public int compareTo(BaseEntity other) {
    if (getId() != null && other.getId() != null) {
      return getId().compareTo(other.getId());
    }

    if (other instanceof Folder) {
      Folder otherF = (Folder) other;
      if (getFullName() != null && otherF.getFullName() != null) {
        return getFullName().compareTo(otherF.getFullName());
      }
    }

    return super.compareTo(other);
  }

}
