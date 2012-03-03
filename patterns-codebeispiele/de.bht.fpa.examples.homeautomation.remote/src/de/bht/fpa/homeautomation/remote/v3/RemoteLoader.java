package de.bht.fpa.homeautomation.remote.v3;

import de.bht.fpa.homeautomation.remote.v3.command.CeilingFanHighCommand;
import de.bht.fpa.homeautomation.remote.v3.command.CeilingFanOffCommand;
import de.bht.fpa.homeautomation.remote.v3.command.ICommand;
import de.bht.fpa.homeautomation.remote.v3.command.NoCommand;
import de.bht.fpa.homeautomation.vendors.CeilingFan;

public class RemoteLoader {
  public static void main(String[] args) {
    Remote remote = new Remote();

    ICommand noCommand = new NoCommand();
    for (int i = 0; i < Remote.NR_OF_SLOTS; i++) {
      remote.setSlot(i, noCommand, noCommand);
    }

    CeilingFan ceilingFan = new CeilingFan();

    remote.setSlot(0, new CeilingFanHighCommand(ceilingFan), new CeilingFanOffCommand(ceilingFan));

    remote.onButtonWasPushed(0);
    remote.onButtonWasPushed(0);
    remote.offButtonWasPushed(0);

    remote.undoButtonWasPushed();
    remote.onButtonWasPushed(0);
    remote.offButtonWasPushed(0);

  }
}
