package de.bht.fpa.statepattern.v5.states;

public class OutOfOrderState implements ICoffeMachineState {

  @Override
  public String insertChip() {
    return "Gerät außer Betrieb.";
  }

  @Override
  public String ecjectChip() {
    return "Gerät außer Betrieb.";
  }

  @Override
  public String selectBeverage() {
    return "Gerät außer Betrieb.";
  }

  @Override
  public String dispenseBeverage() {
    return "Gerät außer Betrieb.";
  }

}
