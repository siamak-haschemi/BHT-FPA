package de.bht.fpa.strategypattern.v2;

public class CSharpEditor extends Editor {

  @Override
  public String format(String string) {
    String temp = insertLineBreaks(string, '{');
    temp = insertLineBreaks(temp, '}');
    return insertLineBreaks(temp, ';');
  }
}
