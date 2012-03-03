package de.bht.fpa.statepattern.v5;

import de.bht.fpa.statepattern.v5.states.ICoffeMachineState;
import de.bht.fpa.statepattern.v5.states.NoChipState;

public class CoffeeMachine {
  private ICoffeMachineState state = new NoChipState(this);
  private int capacity;

  public CoffeeMachine(int capacity) {
    System.out.println("\nWillkommen.");
    this.capacity = capacity;
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

  public ICoffeMachineState getState() {
    return state;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
