package de.bht.fpa.homeautomation.remote.v4.command;

import de.bht.fpa.homeautomation.vendors.OutdoorLight;

public class OutdoorLightOffCommand extends AbstractCommand {
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
