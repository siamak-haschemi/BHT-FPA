package de.bht.fpa.homeautomation.remote.v3.command;

import de.bht.fpa.homeautomation.vendors.GarageDoor;

public class GarageDoorCloseCommand extends AbstractCommand {

  private final GarageDoor garageDoor;

  public GarageDoorCloseCommand(GarageDoor garageDoor) {
    this.garageDoor = garageDoor;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    garageDoor.stop();
    garageDoor.down();
    garageDoor.lightOff();
  }
}
