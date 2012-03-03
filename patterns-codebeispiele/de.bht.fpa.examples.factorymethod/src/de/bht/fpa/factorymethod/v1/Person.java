package de.bht.fpa.factorymethod.v1;

public abstract class Person {
  protected final String name;

  public Person(String name) {
    this.name = name;
  }

  public void initialize() {
    System.out.println(getClass().getName() + ": " + name + ": initialize");
  }

  public void calculateAge() {
    System.out.println(getClass().getName() + ": " + name + ": calculateAge");
  }

  public void printDescription() {
    System.out.println(getClass().getName() + ": " + name + ": printDescription");
  }

}
