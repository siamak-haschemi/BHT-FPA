package de.bht.fpa.examples.composite.safety;

/**
 * The base class of all humanity types. Every person has a name.
 */
public abstract class Person {

  protected String name;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
