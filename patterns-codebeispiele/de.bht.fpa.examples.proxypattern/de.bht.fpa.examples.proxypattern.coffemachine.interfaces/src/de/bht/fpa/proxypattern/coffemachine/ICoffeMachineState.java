package de.bht.fpa.proxypattern.coffemachine;

public interface ICoffeMachineState {

  String insertChip();

  String ecjectChip();

  String selectBeverage();

  String dispenseBeverage();
}
