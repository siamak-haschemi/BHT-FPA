package de.bht.fpa.examples.composite.transparency;

import java.util.LinkedList;
import java.util.List;

/**
 * The base class of all humanity types. Every person has a name.
 */
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

  /**
   * A {@link Person} can have children. Sub-classes can add the specified
   * {@link Person} as a child. The default implementation does not do anything.
   * 
   * @param child
   *          the {@link Person} to add
   * @return <code>true</code> if the {@link Person}s children have changed
   */
  public boolean addChild(Person child) {
    return false;
  }

  /**
   * If a {@link Person} has children, sub-classes can remove the specified
   * {@link Person} in this method. The default implementation does not do
   * anything.
   * 
   * @param child
   *          the {@link Person} to remove
   * @return <code>true</code> if the {@link Person} contained the person
   */
  public boolean removeChild(Person child) {
    return false;
  }

  /**
   * If a {@link Person} hs children, this method returns the children. The
   * default implementation returns an empty list.
   * 
   * @return the children of this {@link Person}, if it has any.
   */
  public List<Person> getChildren() {
    return new LinkedList<Person>();
  }

}
