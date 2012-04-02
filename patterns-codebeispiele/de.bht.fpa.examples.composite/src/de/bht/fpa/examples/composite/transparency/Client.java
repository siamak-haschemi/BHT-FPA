package de.bht.fpa.examples.composite.transparency;

import java.util.List;

/**
 * <p>
 * This class demonstrates the <i>transparent</i> variation of the Composite
 * Design Pattern. It uses the {@link Humanity} class to create a hierarchy of
 * {@link Person}s ( {@link Woman} and {@link Men}).
 * </p>
 * <p>
 * The variation is <i>transparent</i>, because the {@link Person} class
 * contains every method of every sub-class (for example the
 * {@link Woman#addChild(Person)}, {@link Woman#removeChild(Person)}, and the
 * {@link Woman#getChildren()}). The client can therefore use every
 * {@link Person} sub-class <i>transparently</i> and don't care if methods so
 * something useful, or not.
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

    // The Woman class does something useful in the getChildren method, but the
    // Men class does not. However, we can treat every Person sub-type
    // (Woman/Men) equal, and call on each of them the getChildren method.
    List<Person> children = person.getChildren();
    for (Person child : children) {
      printChildren(depth + 1, child);
    }
  }
}
