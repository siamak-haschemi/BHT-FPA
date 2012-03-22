/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class represents an e-mail. It can be used together with JAXB and JPA.
 * 
 * <p>
 * <i>Note that this class can be used together with JAXB and JPA.</i>
 * </p>
 * 
 * @author Siamak Haschemi
 * 
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Message extends BaseEntity {
  private static final long serialVersionUID = -7507357058099089398L;

  /**
   * Note that the ID for messages is not auto-generated in the database,
   * because messages have an unique ID assigned by the IMAP server.
   */
  @Id
  private Long id;

  @Lob
  private String text;

  @Lob
  private String subject;

  @OneToOne(cascade = CascadeType.ALL)
  private Sender sender;

  @Enumerated(EnumType.STRING)
  private Importance importance;

  @Temporal(TemporalType.DATE)
  private Date received;

  @Temporal(TemporalType.DATE)
  private Date sent;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Recipient> recipients = new LinkedList<Recipient>();

  @OneToMany(cascade = CascadeType.ALL)
  private List<Attachment> attachment = new LinkedList<Attachment>();

  @Column(name = "READ_")
  private Boolean read;

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public List<Recipient> getRecipients() {
    return recipients;
  }

  public void setRecipients(List<Recipient> recipient) {
    this.recipients = recipient;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getReceived() {
    return received;
  }

  public void setReceived(Date received) {
    this.received = received;
  }

  @XmlJavaTypeAdapter(DateAdapter.class)
  public Date getSent() {
    return sent;
  }

  public void setSent(Date sent) {
    this.sent = sent;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public List<Attachment> getAttachment() {
    return attachment;
  }

  public void setAttachment(List<Attachment> attachment) {
    this.attachment = attachment;
  }

  public Importance getImportance() {
    return importance;
  }

  public void setImportance(Importance importance) {
    this.importance = importance;
  }

  public Boolean isRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Message: ");
    s.append("id=").append(id).append(" ");
    s.append("sender=").append(sender).append(" ");
    s.append("received=").append(received).append(" ");
    s.append("subject=").append(subject).append(" ");
    s.append("read=").append(read).append(" ");
    s.append("importance=").append(importance).append(" ");

    s.append("attachment=(");
    for (Attachment a : attachment) {
      s.append(a).append(",");
    }
    s.append(")").append(" ");

    s.append("recipient=(");
    for (Recipient r : recipients) {
      s.append(r).append(",");
    }
    s.append(")");

    s.append("text=").append(text);

    s.append("]").append(" ");
    return s.toString();
  }

  public String toShortString() {
    StringBuilder s = new StringBuilder();
    s.append("[Message: ");
    s.append("sender=").append(sender).append(" ");
    s.append("received=").append(received).append(" ");
    s.append("subject=").append(subject).append(" ");
    s.append("read=").append(read).append(" ");
    s.append("importance=").append(importance).append(" ");

    s.append("recipient=(");
    for (Recipient r : recipients) {
      s.append(r).append(",");
    }
    s.append(")");

    s.append("#attachments=" + attachment.size());
    s.append("]").append(" ");
    return s.toString();
  }
}
