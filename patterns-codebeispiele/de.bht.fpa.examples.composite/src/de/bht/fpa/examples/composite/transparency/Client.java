package de.bht.fpa.examples.composite.transparency;

import java.util.List;

/**
 * <p>
 * This class demonstrates the <i>transparent</i> variation of the Composite
 * Design Pattern. It uses the {@link Humanity} class to create a hierarchy of
 * {@link Person}s ( {@link Women} and {@link Men}).
 * </p>
 * <p>
 * The variation is <i>transparent</i>, because very the {@link Person} class
 * contains every method of every sub-type (for example the addChildren,
 * removeChildren, and the getChildren). The client can therefore use every
 * Person sub-type <i>transparently</i> and don't care if the method is
 * implemented, or not.
 * </p>
 */
public final class Client {
  private Client() {
  }

  public static void main(String[] args) {

    Person firstPerson = Humanity.createPersonHierarchy();

    printChildren(0, firstPerson);

  }

  public static void printChildren(int depth, Person person) {
    for (int i = 0; i < depth; i++) {
      System.out.print("  ");
    }
    System.out.println(person.getClass().getSimpleName() + " " + person.getName());

    // The Women class implements the getChildren method, but the Men class does
    // not. However, we can treat every Person sub-type (Women/Men) equal, and
    // call on each of them the getChildren method.
    List<Person> children = person.getChildren();
    for (Person child : children) {
      printChildren(depth + 1, child);
    }
  }
}
