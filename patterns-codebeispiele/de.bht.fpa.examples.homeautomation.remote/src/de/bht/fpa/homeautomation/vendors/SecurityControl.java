package de.bht.fpa.homeautomation.vendors;

public class SecurityControl {
  public void arm() {
    System.out.println(this.getClass().getSimpleName() + ".arm()");
  }

  public void disarm() {
    System.out.println(this.getClass().getSimpleName() + ".disarm()");
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
