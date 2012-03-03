package de.bht.fpa.factorymethod.v5;

public abstract class Mammal {
  protected final String name;

  public Mammal(String name) {
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