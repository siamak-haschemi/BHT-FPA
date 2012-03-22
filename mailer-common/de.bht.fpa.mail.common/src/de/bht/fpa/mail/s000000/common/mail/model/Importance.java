/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The importance of an email. Although there is a RFC
 * (http://tools.ietf.org/html/rfc4021) standard for using the "importance"
 * header to indicate the priority of an email, tools like Microsoft Outlook use
 * other header fields ("X-Priority") with completely different values. <br/>
 * This enumeration is therefore a symbolic value.
 * 
 * @author Siamak Haschemi
 * 
 */
@XmlType
@XmlEnum
public enum Importance {
  @XmlEnumValue("low")
  LOW("low"),

  @XmlEnumValue("normal")
  NORMAL("normal"),

  @XmlEnumValue("high")
  HIGH("high");

  private final String value;

  Importance(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static Importance fromValue(String v) {
    for (Importance c : Importance.values()) {
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
