/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the recipients of an e-mail. The type of recipient (to,
 * cc, bcc) is specified with the {@link RecipientType} class.
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
public class Recipient extends BaseEntity {
  private static final long serialVersionUID = -2628672213043973675L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;

  private String personal;

  @Enumerated(EnumType.STRING)
  private RecipientType type;

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPersonal() {
    return personal;
  }

  public void setPersonal(String firstName) {
    this.personal = firstName;
  }

  public RecipientType getType() {
    return type;
  }

  public void setType(RecipientType recipientType) {
    this.type = recipientType;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Recipient: ");
    s.append("id=").append(id).append(" ");
    s.append("type=").append(type).append(" ");
    s.append("email=").append(email).append(" ");
    s.append("personal=").append(personal).append(" ");
    s.append("]");
    return s.toString();
  }

}
