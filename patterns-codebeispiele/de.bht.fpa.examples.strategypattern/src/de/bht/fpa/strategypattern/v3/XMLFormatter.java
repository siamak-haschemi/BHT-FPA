package de.bht.fpa.strategypattern.v3;

public class XMLFormatter extends AFormatter {

  @Override
  public String format(String string) {
    return insertLineBreaks(string, '>');
  }
}
