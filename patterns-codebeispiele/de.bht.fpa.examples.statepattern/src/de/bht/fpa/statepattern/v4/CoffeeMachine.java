package de.bht.fpa.statepattern.v4;

import de.bht.fpa.statepattern.v4.states.BeverageSelectedState;
import de.bht.fpa.statepattern.v4.states.ChipInsertedState;
import de.bht.fpa.statepattern.v4.states.ICoffeMachineState;
import de.bht.fpa.statepattern.v4.states.NoChipState;
import de.bht.fpa.statepattern.v4.states.OutOfOrderState;

public class CoffeeMachine {
  private final ICoffeMachineState noChipState = new NoChipState(this);
  private final ICoffeMachineState chipInsertedState = new ChipInsertedState(this);
  private final ICoffeMachineState beverageSelectedState = new BeverageSelectedState(this);
  private final ICoffeMachineState outOfOrderState = new OutOfOrderState();

  private ICoffeMachineState state = noChipState;
  private int amountOfBeverage;

  public CoffeeMachine(int amountOfBeverage) {
    System.out.println("\nWillkommen.");
    this.amountOfBeverage = amountOfBeverage;
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

  public int getAmountOfBeverage() {
    return amountOfBeverage;
  }

  public void setAmountOfBeverage(int amountOfBeverage) {
    this.amountOfBeverage = amountOfBeverage;
  }

  public ICoffeMachineState getOutOfOrderState() {
    return outOfOrderState;
  }

}
