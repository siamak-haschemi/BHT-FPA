package de.bht.fpa.statepattern.v4.states;

import de.bht.fpa.statepattern.v4.CoffeeMachine;

public class BeverageSelectedState implements ICoffeMachineState {

  private final CoffeeMachine coffeeMachine;

  public BeverageSelectedState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  @Override
  public String insertChip() {
    return "Bitte warten Sie. Wir bereiten bereits Ihr Getränk zu.";
  }

  @Override
  public String ecjectChip() {
    return "Ihr Chip kann nicht ausgeworfen werden, da Sie bereits ein Getränk gewählt haben.";
  }

  @Override
  public String selectBeverage() {
    return "Sie haben bereits ein Getränk gewählt.";
  }

  @Override
  public String dispenseBeverage() {
    coffeeMachine.setAmountOfBeverage(coffeeMachine.getAmountOfBeverage() - 1);

    if (coffeeMachine.getAmountOfBeverage() == 0) {
      coffeeMachine.setState(coffeeMachine.getOutOfOrderState());
      return "Ihr Getränk wird vorbereitet.Gerät ist nun außer Betrieb.";
    } else {
      coffeeMachine.setState(coffeeMachine.getNoChipState());
      return "Ihr Getränk wird vorbereitet.";
    }
  }
}