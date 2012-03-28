package de.bht.fpa.examples.composite.safety;

/**
 * <p>
 * This class demonstrates the <i>safety</i> variation of the Composite Design
 * Pattern. It uses the {@link Humanity} class to create a hierarchy of
 * {@link Person}s ( {@link Woman} and {@link Men}).
 * </p>
 * <p>
 * The variation is <i>safe</i>, because very class in the {@link Person}
 * type-hierarchy specifies only the methods which do something. For example,
 * only the {@link Woman} class has the {@link Woman#addChild(Person)} method,
 * while the {@link Men} class has not. The client can therefore be <i>safe</i>,
 * that it uses methods which actually do something.
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

    if (!(person instanceof Woman)) {
      return;
    }

    // Since only the Woman class has the getChildren method, we have to cast to
    // women to "see" this method.
    Woman mother = (Woman) person;
    for (Person child : mother.getChildren()) {
      printChildren(depth + 1, child);
    }
  }
}
