package de.bht.fpa.homeautomation.vendors;

public class OutdoorLight {
  public void on() {
    System.out.println(this.getClass().getSimpleName() + ".on()");
  }

  public void off() {
    System.out.println(this.getClass().getSimpleName() + ".off()");
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
