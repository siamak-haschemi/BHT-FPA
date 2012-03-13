package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the filter operation.
 * 
 * @author siamakhaschemi
 * 
 */
public enum FilterOperator {
  CONTAINS("contains"), CONTAINS_NOT("contains not"), STARTS_WITH("starts with"), ENDS_WITH("ends with"), IS("is");

  private String value;

  private FilterOperator(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
