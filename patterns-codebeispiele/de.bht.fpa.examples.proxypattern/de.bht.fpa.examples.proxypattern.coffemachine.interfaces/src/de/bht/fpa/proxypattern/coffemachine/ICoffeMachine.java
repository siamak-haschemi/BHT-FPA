package de.bht.fpa.proxypattern.coffemachine;

public interface ICoffeMachine {

  String insertChip();

  String ecjectChip();

  String selectBeverage();

  String dispenseBeverage();

  void setState(ICoffeMachineState state);

  String getState();

  int getCapacity();

  String getLocation();

  void setCapacity(int capacity);

}