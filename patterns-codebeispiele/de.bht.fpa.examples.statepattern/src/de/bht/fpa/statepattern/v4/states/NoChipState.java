package de.bht.fpa.statepattern.v4.states;

import de.bht.fpa.statepattern.v4.CoffeeMachine;

public class NoChipState implements ICoffeMachineState {

  private final CoffeeMachine coffeeMachine;

  public NoChipState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  @Override
  public String insertChip() {
    coffeeMachine.setState(coffeeMachine.getChipInsertedState());
    return "Sie haben einen Chip eingelegt. Wählen Sie ein Getränk.";
  }

  @Override
  public String ecjectChip() {
    return "Sie haben keinen Chip eingelegt den wir Ihnen auswerfen können.";
  }

  @Override
  public String selectBeverage() {
    return "Sie haben ein Getränk ausgewählt, aber kein Chip eingeworfen.";
  }

  @Override
  public String dispenseBeverage() {
    return "Sie müssen zuerst einen Chip einlegen.";
  }

}
