package de.bht.fpa.examples.composite.transparency;

public class Humanity {

  public static Person createPersonHierarchy() {
    Person abby = new Woman("Abby");

    // children of Abby
    Person sabine = new Woman("Sabine"); // see how we can treat the Women class
    Person klaus = new Man("Klaus"); // ... and the Man class transparently as
                                     // Persons.
    Person tom = new Man("Tom");
    abby.addChild(sabine);
    abby.addChild(klaus);
    abby.addChild(tom);

    // children of Sabine
    Person maria = new Woman("Maria");
    Person jens = new Man("Jens");
    sabine.addChild(maria);
    sabine.addChild(jens);

    // children of Maria
    Person friedrich = new Man("Friedrich");
    Person rudolf = new Man("Rudolf");
    maria.addChild(friedrich);
    maria.addChild(rudolf);

    return abby;
  }

}
