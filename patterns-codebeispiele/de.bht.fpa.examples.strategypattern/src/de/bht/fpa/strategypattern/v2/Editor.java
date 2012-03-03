package de.bht.fpa.strategypattern.v2;

public abstract class Editor {

  public abstract String format(String input);

  protected String insertLineBreaks(String input, char delimiter) {
    String result = "";
    for (char c : input.toCharArray()) {
      result += c;
      if (c == delimiter) {
        result += "\n";
      }
    }
    return result;
  }
}
