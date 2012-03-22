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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents an IMAP account.
 * 
 * <p>
 * <i>Note that this class can be used together with JAXB and JPA.</i>
 * </p>
 * 
 * @author Siamak Haschemi
 */
@Entity
public class Account extends BaseEntity {
  private static final long serialVersionUID = -7660640539811469762L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String host;

  private String username;

  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<Folder> folders = new LinkedList<Folder>();

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String fullName) {
    this.name = fullName;
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public void setFolders(List<Folder> folders) {
    this.folders = folders;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Account: ");
    s.append("id=").append(id).append(" ");
    s.append("name=").append(name).append(" ");
    s.append("host=").append(host).append(" ");
    s.append("username=").append(username).append(" ");
    s.append("password=").append(password).append(" ");
    s.append("folders=(");
    for (Folder f : folders) {
      s.append(f).append(",");
    }
    s.append(")");
    s.append("]").append(" ");
    return s.toString();
  }

}
