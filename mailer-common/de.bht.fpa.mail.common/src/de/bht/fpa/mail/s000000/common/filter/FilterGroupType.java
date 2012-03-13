package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the filter grouping type.
 * 
 * @author siamakhaschemi
 */
public enum FilterGroupType {
  UNION("one"), INTERSECTION("all");

  private String value;

  private FilterGroupType(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
