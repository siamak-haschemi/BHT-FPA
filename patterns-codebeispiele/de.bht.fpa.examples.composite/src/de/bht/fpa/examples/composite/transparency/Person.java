package de.bht.fpa.examples.composite.transparency;

import java.util.LinkedList;
import java.util.List;

public abstract class Person {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Person(String name) {
    this.name = name;
  }

  public boolean addChild(Person child) {
    return false;
  }

  public boolean removeChild(Person child) {
    return false;
  }

  public List<Person> getChildren() {
    return new LinkedList<Person>();
  }

}
