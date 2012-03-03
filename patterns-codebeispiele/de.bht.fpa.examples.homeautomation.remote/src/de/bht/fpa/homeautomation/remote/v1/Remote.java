package de.bht.fpa.homeautomation.remote.v1;

import de.bht.fpa.homeautomation.remote.v1.command.ICommand;

public class Remote {
  private static final int NR_OF_SLOTS = 7;
  private final ICommand[] onButtons = new ICommand[NR_OF_SLOTS];
  private final ICommand[] offButtons = new ICommand[NR_OF_SLOTS];

  public void setSlot(int index, ICommand on, ICommand off) {
    onButtons[index] = on;
    offButtons[index] = off;
  }

  public void onButtonWasPushed(int slotIndex) {
    ICommand command = onButtons[slotIndex];

    if (command == null) {
      return;
    }

    command.execute();
  }

  public void offButtonWasPushed(int slotIndex) {
    ICommand command = offButtons[slotIndex];

    if (command == null) {
      return;
    }

    command.execute();
  }

}
