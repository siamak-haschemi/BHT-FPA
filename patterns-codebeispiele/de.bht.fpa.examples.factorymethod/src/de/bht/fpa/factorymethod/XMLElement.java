package de.bht.fpa.factorymethod;

public class XMLElement {
  private final String gender;
  private final String name;

  public XMLElement(String gender, String name) {
    this.gender = gender;
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public String getName() {
    return name;
  }

}
