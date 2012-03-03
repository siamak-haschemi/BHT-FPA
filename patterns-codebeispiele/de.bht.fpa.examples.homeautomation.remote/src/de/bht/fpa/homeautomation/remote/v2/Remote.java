package de.bht.fpa.homeautomation.remote.v2;

import de.bht.fpa.homeautomation.remote.v2.command.ICommand;

public class Remote {
  static final int NR_OF_SLOTS = 7;
  private final ICommand[] onButtons = new ICommand[NR_OF_SLOTS];
  private final ICommand[] offButtons = new ICommand[NR_OF_SLOTS];

  public void setSlot(int index, ICommand on, ICommand off) {
    onButtons[index] = on;
    offButtons[index] = off;
  }

  public void onButtonWasPushed(int slotIndex) {
    onButtons[slotIndex].execute();
  }

  public void offButtonWasPushed(int slotIndex) {
    offButtons[slotIndex].execute();
  }

}
