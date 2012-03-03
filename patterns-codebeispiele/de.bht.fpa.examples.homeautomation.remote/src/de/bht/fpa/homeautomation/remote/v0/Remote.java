package de.bht.fpa.homeautomation.remote.v0;

import de.bht.fpa.homeautomation.vendors.CeilingFan;
import de.bht.fpa.homeautomation.vendors.CeilingFan.CeilingFanModus;
import de.bht.fpa.homeautomation.vendors.GarageDoor;
import de.bht.fpa.homeautomation.vendors.OutdoorLight;
import de.bht.fpa.homeautomation.vendors.SecurityControl;
import de.bht.fpa.homeautomation.vendors.Stereo;

public class Remote {
  private static final int NR_OF_SLOTS = 7;
  private final Object[] devices = new Object[NR_OF_SLOTS];

  public void setSlot(int index, Object device) {
    devices[index] = device;
  }

  public void onButtonWasPushed(int slotIndex) {
    Object device = devices[slotIndex];

    if (device == null) {
      return;
    }

    if (device instanceof SecurityControl) {
      SecurityControl securityControl = (SecurityControl) device;
      securityControl.arm();
    } else if (device instanceof GarageDoor) {
      GarageDoor garageDoor = (GarageDoor) device;
      garageDoor.lightOn();
      garageDoor.stop();
      garageDoor.up();
    } else if (device instanceof OutdoorLight) {
      OutdoorLight outdoorLight = (OutdoorLight) device;
      outdoorLight.on();
    } else if (device instanceof CeilingFan) {
      CeilingFan ceilingFan = (CeilingFan) device;
      if (ceilingFan.getSpeed() == CeilingFanModus.OFF) {
        ceilingFan.low();
      } else if (ceilingFan.getSpeed() == CeilingFanModus.LOW) {
        ceilingFan.medium();
      } else if (ceilingFan.getSpeed() == CeilingFanModus.MEDIUM) {
        ceilingFan.high();
      }
    } else if (device instanceof Stereo) {
      Stereo stereo = (Stereo) device;
      stereo.on();
    }
  }

  public void offButtonWasPushed(int slotIndex) {
    Object device = devices[slotIndex];

    if (device == null) {
      return;
    }

    if (device instanceof SecurityControl) {
      SecurityControl securityControl = (SecurityControl) device;
      securityControl.disarm();
    } else if (device instanceof GarageDoor) {
      GarageDoor garageDoor = (GarageDoor) device;
      garageDoor.stop();
      garageDoor.down();
      garageDoor.lightOff();
    } else if (device instanceof OutdoorLight) {
      OutdoorLight outdoorLight = (OutdoorLight) device;
      outdoorLight.off();
    } else if (device instanceof CeilingFan) {
      CeilingFan ceilingFan = (CeilingFan) device;
      if (ceilingFan.getSpeed() == CeilingFanModus.HIGH) {
        ceilingFan.medium();
      } else if (ceilingFan.getSpeed() == CeilingFanModus.MEDIUM) {
        ceilingFan.low();
      } else if (ceilingFan.getSpeed() == CeilingFanModus.LOW) {
        ceilingFan.off();
      }
    } else if (device instanceof Stereo) {
      Stereo stereo = (Stereo) device;
      stereo.off();
    }
  }
}
