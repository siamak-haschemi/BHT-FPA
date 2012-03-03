package de.bht.fpa.strategypattern.v3;

public class BracesAndSemicolonFormatter extends AFormatter {

  @Override
  public String format(String input) {
    String temp = insertLineBreaks(input, '{');
    temp = insertLineBreaks(temp, '}');
    return insertLineBreaks(temp, ';');
  }
}
