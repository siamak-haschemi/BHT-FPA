package de.bht.fpa.examples.composite.transparency;

import java.util.List;

public class Client {

  /**
   * @param args
   */
  public static void main(String[] args) {

    Person firstPerson = Humanity.getFirstPerson();

    printChildren(1, firstPerson);

  }

  public static void printChildren(int depth, Person person) {
    System.out.println("" + depth + "-Person " + person.getName());
    List<Person> children = person.getChildren();
    depth++;
    for (Person child : children) {
      printChildren(depth, child);
    }
  }
}
