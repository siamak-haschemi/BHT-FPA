package de.bht.fpa.statepattern.v2;

import de.bht.fpa.statepattern.v2.states.BeverageSelectedState;
import de.bht.fpa.statepattern.v2.states.ChipInsertedState;
import de.bht.fpa.statepattern.v2.states.NoChipState;

public class CoffeeMachine {
  private final Object noChipState = new NoChipState(this);
  private final Object chipInsertedState = new ChipInsertedState(this);
  private final Object beverageSelectedState = new BeverageSelectedState(this);

  private Object state = noChipState;

  public CoffeeMachine() {
    System.out.println("\nWillkommen.");
  }

  public String insertChip() {
    if (state instanceof NoChipState) {
      return ((NoChipState) state).insertChip();
    } else if (state instanceof ChipInsertedState) {
      return ((ChipInsertedState) state).insertChip();
    } else if (state instanceof BeverageSelectedState) {
      return ((BeverageSelectedState) state).insertChip();
    }
    throw new IllegalStateException();
  }

  public String ecjectChip() {
    if (state instanceof NoChipState) {
      return ((NoChipState) state).ecjectChip();
    } else if (state instanceof ChipInsertedState) {
      return ((ChipInsertedState) state).ecjectChip();
    } else if (state instanceof BeverageSelectedState) {
      return ((BeverageSelectedState) state).ecjectChip();
    }
    throw new IllegalStateException();
  }

  public String selectBeverage() {
    if (state instanceof NoChipState) {
      return ((NoChipState) state).selectBeverage();
    } else if (state instanceof ChipInsertedState) {
      return ((ChipInsertedState) state).selectBeverage();
    } else if (state instanceof BeverageSelectedState) {
      return ((BeverageSelectedState) state).selectBeverage();
    }
    throw new IllegalStateException();
  }

  public String dispenseBeverage() {
    if (state instanceof NoChipState) {
      return ((NoChipState) state).dispenseBeverage();
    } else if (state instanceof ChipInsertedState) {
      return ((ChipInsertedState) state).dispenseBeverage();
    } else if (state instanceof BeverageSelectedState) {
      return ((BeverageSelectedState) state).dispenseBeverage();
    }
    throw new IllegalStateException();
  }

  public void setState(Object state) {
    this.state = state;
  }

  public Object getNoChipState() {
    return noChipState;
  }

  public Object getChipInsertedState() {
    return chipInsertedState;
  }

  public Object getBeverageSelectedState() {
    return beverageSelectedState;
  }
}
