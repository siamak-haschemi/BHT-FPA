package de.bht.fpa.homeautomation.remote.v1.command;

import de.bht.fpa.homeautomation.vendors.GarageDoor;

public class GarageDoorOpenCommand implements ICommand {

  private final GarageDoor garageDoor;

  public GarageDoorOpenCommand(GarageDoor garageDoor) {
    this.garageDoor = garageDoor;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    garageDoor.lightOn();
    garageDoor.stop(); // FIXME
    garageDoor.up();
  }
}
