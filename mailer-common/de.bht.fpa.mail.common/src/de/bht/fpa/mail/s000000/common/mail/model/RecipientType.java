/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * This class represents the type of the recipient of an e-mail.
 * 
 * @author Siamak Haschemi
 * 
 */
public enum RecipientType {
  @XmlEnumValue("to")
  TO("to"),

  @XmlEnumValue("cc")
  CC("cc"),

  @XmlEnumValue("bcc")
  BCC("bcc");

  private final String value;

  RecipientType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static RecipientType fromValue(String v) {
    for (RecipientType c : RecipientType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

  @Override
  public String toString() {
    return value;
  }
}
