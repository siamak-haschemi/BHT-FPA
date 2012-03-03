package de.bht.fpa.strategypattern.v1;

public class Editor {

  private EditorType type = null;

  public void setType(EditorType type) {
    this.type = type;
  }

  public String format(String input) {
    if (type == null) {
      throw new RuntimeException();
    }

    String temp;
    switch (type) {
    case CSHARP:
      temp = insertLineBreaks(input, '{');
      temp = insertLineBreaks(temp, '}');
      return insertLineBreaks(temp, ';');
    case JAVA:
      temp = insertLineBreaks(input, '{');
      temp = insertLineBreaks(temp, '}');
      return insertLineBreaks(temp, ';');
    case XML:
      return insertLineBreaks(input, '>');
    }

    throw new IllegalArgumentException();
  }

  private String insertLineBreaks(String input, char delimiter) {
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
