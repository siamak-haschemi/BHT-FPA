package de.bht.fpa.homeautomation.remote.v4.command;

import de.bht.fpa.homeautomation.vendors.CeilingFan;
import de.bht.fpa.homeautomation.vendors.CeilingFan.CeilingFanModus;

public class CeilingFanOffCommand extends AbstractCommand {

  private final CeilingFan ceilingFan;
  private CeilingFanModus oldSpeed;

  public CeilingFanOffCommand(CeilingFan ceilingFan) {
    this.ceilingFan = ceilingFan;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    oldSpeed = ceilingFan.getSpeed();
    ceilingFan.off();
  }

  @Override
  public void undo() {
    System.out.println(this.getClass().getSimpleName() + ".undo()");
    switch (oldSpeed) {
    case OFF:
      ceilingFan.off();
      break;
    case LOW:
      ceilingFan.low();
      break;
    case MEDIUM:
      ceilingFan.medium();
      break;
    case HIGH:
      ceilingFan.high();
      break;
    }
  }
}
