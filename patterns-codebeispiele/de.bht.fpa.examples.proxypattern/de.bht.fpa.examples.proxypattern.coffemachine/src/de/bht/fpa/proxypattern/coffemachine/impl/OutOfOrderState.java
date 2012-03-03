package de.bht.fpa.proxypattern.coffemachine.impl;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachineState;

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
