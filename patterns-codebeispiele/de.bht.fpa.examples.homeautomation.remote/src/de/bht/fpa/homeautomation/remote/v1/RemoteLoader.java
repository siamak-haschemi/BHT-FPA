package de.bht.fpa.homeautomation.remote.v1;

import de.bht.fpa.homeautomation.remote.v1.command.GarageDoorCloseCommand;
import de.bht.fpa.homeautomation.remote.v1.command.GarageDoorOpenCommand;
import de.bht.fpa.homeautomation.remote.v1.command.OutdoorLightOffCommand;
import de.bht.fpa.homeautomation.remote.v1.command.OutdoorLightOnCommand;
import de.bht.fpa.homeautomation.vendors.GarageDoor;
import de.bht.fpa.homeautomation.vendors.OutdoorLight;

public class RemoteLoader {
  public static void main(String[] args) {
    Remote remote = new Remote();

    OutdoorLight outdoorLight = new OutdoorLight();
    GarageDoor garageDoor = new GarageDoor();

    remote.setSlot(0, new OutdoorLightOnCommand(outdoorLight), new OutdoorLightOffCommand(outdoorLight));
    remote.setSlot(1, new GarageDoorOpenCommand(garageDoor), new GarageDoorCloseCommand(garageDoor));

    remote.onButtonWasPushed(0);
    remote.offButtonWasPushed(0);
    remote.onButtonWasPushed(1);
    remote.offButtonWasPushed(1);
  }
}
