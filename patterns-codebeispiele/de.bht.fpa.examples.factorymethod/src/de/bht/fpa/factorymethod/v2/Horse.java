package de.bht.fpa.factorymethod.v2;

public abstract class Horse {
  protected final String name;

  public Horse(String name) {
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
