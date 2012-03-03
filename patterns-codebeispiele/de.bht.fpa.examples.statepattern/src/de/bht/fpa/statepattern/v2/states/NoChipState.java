package de.bht.fpa.statepattern.v2.states;

import de.bht.fpa.statepattern.v2.CoffeeMachine;

public class NoChipState {

  private final CoffeeMachine coffeeMachine;

  public NoChipState(CoffeeMachine coffeeMachine) {
    this.coffeeMachine = coffeeMachine;
  }

  public String insertChip() {
    coffeeMachine.setState(coffeeMachine.getChipInsertedState());
    return "Sie haben einen Chip eingelegt. Wählen Sie ein Getränk.";
  }

  public String ecjectChip() {
    return "Sie haben keinen Chip eingelegt den wir Ihnen auswerfen können.";
  }

  public String selectBeverage() {
    return "Sie haben ein Getränk ausgewählt, aber kein Chip eingeworfen.";
  }

  public String dispenseBeverage() {
    return "Sie müssen zuerst einen Chip einlegen.";
  }

}
