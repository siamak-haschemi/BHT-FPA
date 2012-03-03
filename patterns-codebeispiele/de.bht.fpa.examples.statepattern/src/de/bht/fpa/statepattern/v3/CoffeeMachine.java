package de.bht.fpa.statepattern.v3;

import de.bht.fpa.statepattern.v3.states.BeverageSelectedState;
import de.bht.fpa.statepattern.v3.states.ChipInsertedState;
import de.bht.fpa.statepattern.v3.states.ICoffeMachineState;
import de.bht.fpa.statepattern.v3.states.NoChipState;

public class CoffeeMachine {
  private final ICoffeMachineState noChipState = new NoChipState(this);
  private final ICoffeMachineState chipInsertedState = new ChipInsertedState(this);
  private final ICoffeMachineState beverageSelectedState = new BeverageSelectedState(this);

  private ICoffeMachineState state = noChipState;

  public CoffeeMachine() {
    System.out.println("\nWillkommen.");
  }

  public String insertChip() {
    return state.insertChip();
  }

  public String ecjectChip() {
    return state.ecjectChip();
  }

  public String selectBeverage() {
    return state.selectBeverage();
  }

  public String dispenseBeverage() {
    return state.dispenseBeverage();
  }

  public void setState(ICoffeMachineState state) {
    this.state = state;
  }

  public ICoffeMachineState getNoChipState() {
    return noChipState;
  }

  public ICoffeMachineState getChipInsertedState() {
    return chipInsertedState;
  }

  public ICoffeMachineState getBeverageSelectedState() {
    return beverageSelectedState;
  }
}
