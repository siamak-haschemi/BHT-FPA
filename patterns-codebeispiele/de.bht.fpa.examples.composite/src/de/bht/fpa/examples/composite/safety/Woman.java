package de.bht.fpa.examples.composite.safety;

import java.util.LinkedList;
import java.util.List;

/**
 * A women is a person, but also has a children of persons she borned.
 */
public class Woman extends Person {
  private final List<Person> children = new LinkedList<Person>();

  public Woman(String name) {
    super(name);
  }

  /**
   * Adds the specified {@link Person} as a child of this {@link Woman}.
   * 
   * @param child
   *          the {@link Person} to add
   * @return <code>true</code> if the womens children have changed
   */
  public boolean addChild(Person child) {
    return children.add(child);
  }

  /**
   * Removes the specified {@link Person} from the children of this
   * {@link Woman}.
   * 
   * @param child
   *          the {@link Person} to remove
   * @return <code>true</code> if the women contained the person
   */
  public boolean removeChild(Person child) {
    return children.remove(child);
  }

  /**
   * Returns the children of this {@link Woman}.
   * 
   * @return the children of this {@link Woman}.
   */
  public List<Person> getChildren() {
    return children;
  }
}
