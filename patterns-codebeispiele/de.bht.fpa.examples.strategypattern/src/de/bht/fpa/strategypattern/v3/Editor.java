package de.bht.fpa.strategypattern.v3;

public class Editor {

  private AFormatter formatter;

  public String format(String string) {
    return formatter.format(string);
  }

  public void setFormatter(AFormatter formatter) {
    this.formatter = formatter;
  }
}
