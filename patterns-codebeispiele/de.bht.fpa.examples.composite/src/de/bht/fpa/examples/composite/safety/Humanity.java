package de.bht.fpa.examples.composite.safety;

public final class Humanity {
  private Humanity() {
  }

  public static Person createPersonHierarchy() {
    Woman abby = new Woman("Abby");

    // We cannot use the Person class here, since it has not the addChildren
    // method. This won't work:
    // Person abby = new Woman("Sabine");

    // children of Abby
    Woman sabine = new Woman("Sabine");
    Person klaus = new Man("Klaus");
    Person tom = new Man("Tom");
    abby.addChild(sabine);
    abby.addChild(klaus);
    abby.addChild(tom);

    // children of Sabine
    Woman maria = new Woman("Maria");
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
