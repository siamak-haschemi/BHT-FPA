package de.bht.fpa.mail.common.model;

import javax.xml.bind.annotation.XmlEnumValue;

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
