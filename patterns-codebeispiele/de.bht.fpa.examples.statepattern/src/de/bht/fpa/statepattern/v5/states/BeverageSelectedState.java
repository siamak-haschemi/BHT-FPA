package de.bht.fpa.statepattern.v5.states;

import de.bht.fpa.statepattern.v5.CoffeeMachine;

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
    coffeeMachine.setCapacity(coffeeMachine.getCapacity() - 1);

    String result = "Ihr Getränk wird vorbereitet.";

    if (coffeeMachine.getCapacity() == 0) {
      coffeeMachine.setState(new OutOfOrderState());
      return result + "Gerät ist nun außer Betrieb.";
    } else {
      coffeeMachine.setState(new NoChipState(coffeeMachine));
      return result;
    }
  }

}
