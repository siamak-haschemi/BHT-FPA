package de.bht.fpa.proxypattern.coffemachine.impl;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;
import de.bht.fpa.proxypattern.coffemachine.ICoffeMachineState;

public class CoffeeMachine implements ICoffeMachine {
  private ICoffeMachineState state = new NoChipState(this);
  private int capacity;
  private final String location;

  public CoffeeMachine(String location, int capacity) {
    this.location = location;
    this.capacity = capacity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#insertChip()
   */
  @Override
  public String insertChip() {
    return state.insertChip();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#ecjectChip()
   */
  @Override
  public String ecjectChip() {
    return state.ecjectChip();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#selectBeverage()
   */
  @Override
  public String selectBeverage() {
    return state.selectBeverage();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#dispenseBeverage()
   */
  @Override
  public String dispenseBeverage() {
    return state.dispenseBeverage();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#setState(de.bht.fpa.
   * proxypattern.coffemachine.states.ICoffeMachineState)
   */
  @Override
  public void setState(ICoffeMachineState state) {
    this.state = state;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#getState()
   */
  @Override
  public String getState() {
    return state.getClass().getSimpleName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#getCapacity()
   */
  @Override
  public int getCapacity() {
    return capacity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#getLocation()
   */
  @Override
  public String getLocation() {
    return location;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fpa.proxypattern.coffemachine.ICoffeMachine#setCapacity(int)
   */
  @Override
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
