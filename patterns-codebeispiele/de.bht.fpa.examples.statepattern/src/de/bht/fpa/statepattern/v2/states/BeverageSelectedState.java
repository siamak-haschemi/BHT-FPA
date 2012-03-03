package de.bht.fpa.statepattern.v2.states;

import de.bht.fpa.statepattern.v2.CoffeeMachine;

public class BeverageSelectedState {

  private final CoffeeMachine coffeeMachine;

  public BeverageSelectedState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  public String insertChip() {
    return "Bitte warten Sie. Wir bereiten bereits Ihr Getränk zu.";
  }

  public String ecjectChip() {
    return "Ihr Chip kann nicht ausgeworfen werden, da Sie bereits ein Getränk gewählt haben.";
  }

  public String selectBeverage() {
    return "Sie haben bereits ein Getränk gewählt.";
  }

  public String dispenseBeverage() {
    coffeeMachine.setState(coffeeMachine.getNoChipState());
    return "Ihr Getränk wird vorbereitet.";
  }

}
