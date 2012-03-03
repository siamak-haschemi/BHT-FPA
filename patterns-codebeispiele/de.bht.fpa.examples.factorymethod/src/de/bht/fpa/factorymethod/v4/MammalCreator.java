package de.bht.fpa.factorymethod.v4;

public abstract class MammalCreator {
  protected abstract Mammal createMammal(String gender, String name);
}
