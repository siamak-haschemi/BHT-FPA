package de.bht.fpa.homeautomation.remote.v2.command;

import de.bht.fpa.homeautomation.vendors.OutdoorLight;

public class OutdoorLightOffCommand implements ICommand {
  private final OutdoorLight outdoorLight;

  public OutdoorLightOffCommand(OutdoorLight outdoorLight) {
    this.outdoorLight = outdoorLight;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    outdoorLight.off();
  }
}
