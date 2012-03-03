package de.bht.fpa.homeautomation.vendors;

public class CeilingFan {
  public enum CeilingFanModus {
    OFF, LOW, MEDIUM, HIGH;
  }

  private CeilingFanModus speed = CeilingFanModus.OFF;

  public void high() {
    System.out.println(this.getClass().getSimpleName() + ".high()");
    speed = CeilingFanModus.HIGH;
  }

  public void medium() {
    System.out.println(this.getClass().getSimpleName() + ".medium()");
    speed = CeilingFanModus.MEDIUM;
  }

  public void low() {
    System.out.println(this.getClass().getSimpleName() + ".low()");
    speed = CeilingFanModus.LOW;
  }

  public void off() {
    System.out.println(this.getClass().getSimpleName() + ".off()");
    speed = CeilingFanModus.OFF;
  }

  public CeilingFanModus getSpeed() {
    System.out.println(this.getClass().getSimpleName() + ".getSpeed() ==> " + speed);
    return speed;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
