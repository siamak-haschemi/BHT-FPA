package de.bht.fpa.examples.composite.safety;

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
    if (person instanceof Woman) {
      Woman mother = (Woman) person;
      List<Person> children = mother.getChildren();
      depth++;
      for (Person child : children) {
        printChildren(depth, child);
      }
    }
  }
}
