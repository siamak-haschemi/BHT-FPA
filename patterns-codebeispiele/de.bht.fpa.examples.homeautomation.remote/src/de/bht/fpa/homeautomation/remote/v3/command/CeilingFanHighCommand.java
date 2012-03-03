package de.bht.fpa.homeautomation.remote.v3.command;

import de.bht.fpa.homeautomation.vendors.CeilingFan;
import de.bht.fpa.homeautomation.vendors.CeilingFan.CeilingFanModus;

public class CeilingFanHighCommand extends AbstractCommand {

  private final CeilingFan ceilingFan;
  private CeilingFanModus oldSpeed;

  public CeilingFanHighCommand(CeilingFan ceilingFan) {
    this.ceilingFan = ceilingFan;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    oldSpeed = ceilingFan.getSpeed();
    switch (oldSpeed) {
    case OFF:
      ceilingFan.low();
      break;
    case LOW:
      ceilingFan.medium();
      break;
    case MEDIUM:
      ceilingFan.high();
      break;
    }
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
