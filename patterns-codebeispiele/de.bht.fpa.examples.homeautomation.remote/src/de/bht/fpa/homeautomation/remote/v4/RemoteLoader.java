package de.bht.fpa.homeautomation.remote.v4;

import de.bht.fpa.homeautomation.remote.v4.command.CeilingFanHighCommand;
import de.bht.fpa.homeautomation.remote.v4.command.CeilingFanOffCommand;
import de.bht.fpa.homeautomation.remote.v4.command.GarageDoorCloseCommand;
import de.bht.fpa.homeautomation.remote.v4.command.GarageDoorOpenCommand;
import de.bht.fpa.homeautomation.remote.v4.command.ICommand;
import de.bht.fpa.homeautomation.remote.v4.command.MacroCommand;
import de.bht.fpa.homeautomation.remote.v4.command.NoCommand;
import de.bht.fpa.homeautomation.remote.v4.command.OutdoorLightOffCommand;
import de.bht.fpa.homeautomation.remote.v4.command.OutdoorLightOnCommand;
import de.bht.fpa.homeautomation.remote.v4.command.StereoOffCommand;
import de.bht.fpa.homeautomation.remote.v4.command.StereoWithDvdOnCommand;
import de.bht.fpa.homeautomation.vendors.CeilingFan;
import de.bht.fpa.homeautomation.vendors.GarageDoor;
import de.bht.fpa.homeautomation.vendors.OutdoorLight;
import de.bht.fpa.homeautomation.vendors.Stereo;

public class RemoteLoader {
  public static void main(String[] args) {
    Remote remote = new Remote();

    ICommand noCommand = new NoCommand();
    for (int i = 0; i < Remote.NR_OF_SLOTS; i++) {
      remote.setSlot(i, noCommand, noCommand);
    }

    CeilingFan ceilingFan = new CeilingFan();
    OutdoorLight outdoorLight = new OutdoorLight();
    GarageDoor garageDoor = new GarageDoor();
    Stereo stereo = new Stereo();

    remote.setSlot(0, new OutdoorLightOnCommand(outdoorLight), new OutdoorLightOffCommand(outdoorLight));
    remote.setSlot(1, new GarageDoorOpenCommand(garageDoor), new GarageDoorCloseCommand(garageDoor));
    remote.setSlot(2, new CeilingFanHighCommand(ceilingFan), new CeilingFanOffCommand(ceilingFan));

    MacroCommand partyOnGarth = new MacroCommand(new StereoWithDvdOnCommand(stereo), new OutdoorLightOnCommand(
        outdoorLight), new GarageDoorCloseCommand(garageDoor));

    MacroCommand partyOffGarth = new MacroCommand(new StereoOffCommand(stereo),
        new OutdoorLightOffCommand(outdoorLight), new GarageDoorOpenCommand(garageDoor));

    remote.setSlot(3, partyOnGarth, partyOffGarth);

    remote.onButtonWasPushed(3);
    remote.offButtonWasPushed(3);

  }
}
