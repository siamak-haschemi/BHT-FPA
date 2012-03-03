package de.bht.fpa.homeautomation.vendors;

public class GarageDoor {
  public void up() {
    System.out.println(this.getClass().getSimpleName() + ".up()");
  }

  public void down() {
    System.out.println(this.getClass().getSimpleName() + ".down()");
  }

  public void stop() {
    System.out.println(this.getClass().getSimpleName() + ".stop()");
  }

  public void lightOn() {
    System.out.println(this.getClass().getSimpleName() + ".lightOn()");
  }

  public void lightOff() {
    System.out.println(this.getClass().getSimpleName() + ".lightOff()");
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
