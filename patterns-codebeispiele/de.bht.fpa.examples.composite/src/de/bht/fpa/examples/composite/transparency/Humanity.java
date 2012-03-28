package de.bht.fpa.examples.composite.transparency;

public class Humanity {

  public static Person getFirstPerson() {
    Woman abby = new Woman("Abby");

    // children of Abby
    Woman sabine = new Woman("Sabine");
    Man klaus = new Man("Klaus");
    Man tom = new Man("Tom");
    abby.addChild(sabine);
    abby.addChild(klaus);
    abby.addChild(tom);

    // children of Sabine
    Woman maria = new Woman("Maria");
    Man jens = new Man("Jens");
    sabine.addChild(maria);
    sabine.addChild(jens);

    // children of Maria
    Man friedrich = new Man("Friedrich");
    Man rudolf = new Man("Rudolf");
    maria.addChild(friedrich);
    maria.addChild(rudolf);

    return abby;
  }

}
