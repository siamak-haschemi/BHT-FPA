package de.bht.fpa.homeautomation.remote.v1.command;

import de.bht.fpa.homeautomation.vendors.GarageDoor;

public class GarageDoorCloseCommand implements ICommand {

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
