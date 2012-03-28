package de.bht.fpa.examples.composite.transparency;

import java.util.LinkedList;
import java.util.List;

public class Woman extends Person {
  private final List<Person> children = new LinkedList<Person>();

  public Woman(String name) {
    super(name);
  }

  public boolean addChild(Person child) {
    return children.add(child);
  }

  public boolean removeChild(Person child) {
    return children.remove(child);
  }

  public List<Person> getChildren() {
    return children;
  }
}
