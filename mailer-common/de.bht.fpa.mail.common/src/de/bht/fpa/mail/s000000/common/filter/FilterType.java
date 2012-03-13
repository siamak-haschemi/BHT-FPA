package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the fields of the email to filter.
 * 
 * @author siamakhaschemi
 */
public enum FilterType {
  SENDER("Sender"), RECEIVER("Receiver"), SUBJECT("Subject"), TEXT("Contents of EMail"), READ("Read"), IMPORTANCE(
      "Importance");

  private String value;

  private FilterType(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
