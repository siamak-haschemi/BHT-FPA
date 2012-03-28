package de.bht.fpa.examples.composite.transparency;

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
   * Since a Women has children, we have to override this method and store the
   * given {@link Person}.
   * 
   * @see de.bht.fpa.examples.composite.transparency.Person#addChild(de.bht.fpa.examples.composite.transparency.Person)
   */
  @Override
  public boolean addChild(Person child) {
    return children.add(child);
  }

  /**
   * Since a Women has children, we have to override this method and remove the
   * given {@link Person}.
   * 
   * @see de.bht.fpa.examples.composite.transparency.Person#removeChild(de.bht.fpa.examples.composite.transparency.Person)
   */
  @Override
  public boolean removeChild(Person child) {
    return children.remove(child);
  }

  /**
   * Since a Women has children, we have to override this method and return the
   * list of our children.
   * 
   * @see de.bht.fpa.examples.composite.transparency.Person#getChildren()
   */
  @Override
  public List<Person> getChildren() {
    return children;
  }

}
