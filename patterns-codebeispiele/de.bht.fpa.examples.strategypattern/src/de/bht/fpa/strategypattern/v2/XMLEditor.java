package de.bht.fpa.strategypattern.v2;

public class XMLEditor extends Editor {

  @Override
  public String format(String string) {
    return insertLineBreaks(string, '>');
  }
}
