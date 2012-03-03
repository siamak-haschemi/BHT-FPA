package de.bht.fpa.statepattern.v4.states;

public interface ICoffeMachineState {

  String insertChip();

  String ecjectChip();

  String selectBeverage();

  String dispenseBeverage();
}
