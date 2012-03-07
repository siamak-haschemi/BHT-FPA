package de.bht.fpa.mail.s000000.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

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
