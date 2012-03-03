package de.bht.fpa.homeautomation.remote.v0;

import de.bht.fpa.homeautomation.remote.v0.Remote;
import de.bht.fpa.homeautomation.vendors.GarageDoor;
import de.bht.fpa.homeautomation.vendors.OutdoorLight;

public class RemoteLoader {
  public static void main(String[] args) {
    Remote remote = new Remote();

    OutdoorLight outdoorLight = new OutdoorLight();
    GarageDoor garageDoor = new GarageDoor();

    remote.setSlot(0, outdoorLight);
    remote.setSlot(1, garageDoor);

    remote.onButtonWasPushed(0);
    remote.offButtonWasPushed(0);
    remote.onButtonWasPushed(1);
    remote.offButtonWasPushed(1);
  }
}
