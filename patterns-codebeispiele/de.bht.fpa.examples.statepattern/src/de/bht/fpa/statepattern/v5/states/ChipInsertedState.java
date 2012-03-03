package de.bht.fpa.statepattern.v5.states;

import de.bht.fpa.statepattern.v5.CoffeeMachine;

public class ChipInsertedState implements ICoffeMachineState {

  private final CoffeeMachine coffeeMachine;

  public ChipInsertedState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  @Override
  public String insertChip() {
    return "Sie haben bereits einen Chip eingelegt.";
  }

  @Override
  public String ecjectChip() {
    coffeeMachine.setState(new NoChipState(coffeeMachine));
    return "Ihr eingelegter Chip wird ausgeworfen.";
  }

  @Override
  public String selectBeverage() {
    coffeeMachine.setState(new BeverageSelectedState(coffeeMachine));
    String result = "Sie haben ein Getränk ausgewählt.";
    return result + coffeeMachine.dispenseBeverage();
  }

  @Override
  public String dispenseBeverage() {
    return "Sie haben ein Chip eingelegt, aber noch kein ein Getränk ausgewählt.";
  }

}
