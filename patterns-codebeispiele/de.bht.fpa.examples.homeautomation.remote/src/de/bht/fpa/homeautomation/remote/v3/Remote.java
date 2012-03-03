package de.bht.fpa.homeautomation.remote.v3;

import de.bht.fpa.homeautomation.remote.v3.command.ICommand;
import de.bht.fpa.homeautomation.remote.v3.command.NoCommand;

public class Remote {
  static final int NR_OF_SLOTS = 7;
  private final ICommand[] onButtons = new ICommand[NR_OF_SLOTS];
  private final ICommand[] offButtons = new ICommand[NR_OF_SLOTS];
  private ICommand lastCommand = new NoCommand();

  public void setSlot(int index, ICommand on, ICommand off) {
    onButtons[index] = on;
    offButtons[index] = off;
  }

  public void onButtonWasPushed(int slotIndex) {
    lastCommand = onButtons[slotIndex];
    lastCommand.execute();
  }

  public void offButtonWasPushed(int slotIndex) {
    lastCommand = offButtons[slotIndex];
    lastCommand.execute();
  }

  public void undoButtonWasPushed() {
    lastCommand.undo();
  }

}
