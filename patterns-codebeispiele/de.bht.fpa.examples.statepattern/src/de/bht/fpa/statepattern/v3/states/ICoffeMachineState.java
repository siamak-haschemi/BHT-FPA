package de.bht.fpa.statepattern.v3.states;

public interface ICoffeMachineState {

  String insertChip();

  String ecjectChip();

  String selectBeverage();

  String dispenseBeverage();
}
