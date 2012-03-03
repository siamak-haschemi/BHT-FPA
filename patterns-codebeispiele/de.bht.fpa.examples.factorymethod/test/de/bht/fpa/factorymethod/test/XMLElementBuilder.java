package de.bht.fpa.factorymethod.test;

import de.bht.fpa.factorymethod.XMLElement;

public class XMLElementBuilder {
  private String gender = null;
  private String name = null;

  private XMLElementBuilder() {
  }

  public XMLElementBuilder(String gender, String name) {
    this.gender = gender;
    this.name = name;
  }

  public static XMLElementBuilder newXMLElementBuilder() {
    return new XMLElementBuilder();
  }

  public XMLElementBuilder gender(String gender) {
    this.gender = gender;
    return this;
  }

  public XMLElementBuilder name(String name) {
    this.name = name;
    return this;
  }

  public XMLElementBuilder but() {
    return new XMLElementBuilder(gender, name);
  }

  public XMLElement build() {
    return new XMLElement(gender, name);
  }
}
