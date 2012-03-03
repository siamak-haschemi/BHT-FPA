package de.bht.fpa.homeautomation.remote.v3.command;

import de.bht.fpa.homeautomation.vendors.GarageDoor;

public class GarageDoorOpenCommand extends AbstractCommand {

  private final GarageDoor garageDoor;

  public GarageDoorOpenCommand(GarageDoor garageDoor) {
    this.garageDoor = garageDoor;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    garageDoor.lightOn();
    garageDoor.stop();
    garageDoor.up();
  }
}
