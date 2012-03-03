package de.bht.fpa.factorymethod.v5;

import java.util.LinkedList;
import java.util.List;

public class Mare extends Horse {
  private final List<Horse> children = new LinkedList<Horse>();

  public Mare(String name) {
    super(name);
  }

  public boolean addChild(Horse child) {
    return children.add(child);
  }

  public boolean removeChild(Horse child) {
    return children.remove(child);
  }

}
