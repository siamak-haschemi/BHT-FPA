package de.bht.fpa.statepattern.v2.states;

import de.bht.fpa.statepattern.v2.CoffeeMachine;

public class ChipInsertedState {

  private final CoffeeMachine coffeeMachine;

  public ChipInsertedState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  public String insertChip() {
    return "Sie haben bereits einen Chip eingelegt.";
  }

  public String ecjectChip() {
    coffeeMachine.setState(coffeeMachine.getNoChipState());
    return "Ihr eingelegter Chip wird ausgeworfen.";
  }

  public String selectBeverage() {
    coffeeMachine.setState(coffeeMachine.getBeverageSelectedState());
    String result = "Sie haben ein Getränk ausgewählt.";
    return result + coffeeMachine.dispenseBeverage();
  }

  public String dispenseBeverage() {
    return "Sie haben ein Chip eingelegt, aber noch kein ein Getränk ausgewählt.";
  }

}
