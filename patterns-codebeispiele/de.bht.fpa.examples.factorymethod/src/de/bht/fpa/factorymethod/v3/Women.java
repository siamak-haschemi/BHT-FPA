package de.bht.fpa.factorymethod.v3;

import java.util.LinkedList;
import java.util.List;

public class Women extends Person {
  private final List<Person> children = new LinkedList<Person>();

  public Women(String name) {
    super(name);
  }

  public boolean addChild(Person child) {
    return children.add(child);
  }

  public boolean removeChild(Person child) {
    return children.remove(child);
  }
}
