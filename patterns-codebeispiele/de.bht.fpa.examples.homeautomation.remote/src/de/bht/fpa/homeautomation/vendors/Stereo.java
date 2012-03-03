package de.bht.fpa.homeautomation.vendors;

public class Stereo {
  private int vol;

  public void on() {
    System.out.println(this.getClass().getSimpleName() + ".on()");
  }

  public void off() {
    System.out.println(this.getClass().getSimpleName() + ".off()");
  }

  public void setCd() {
    System.out.println(this.getClass().getSimpleName() + ".setCd()");
  }

  public void setDvd() {
    System.out.println(this.getClass().getSimpleName() + ".setDvd()");
  }

  public void setRadio() {
    System.out.println(this.getClass().getSimpleName() + ".setRadio()");
  }

  public void setVolume(int vol) {
    System.out.println(this.getClass().getSimpleName() + ".setVolume(" + vol + ")");
    this.vol = vol;
  }

  public int getVolume() {
    System.out.println(this.getClass().getSimpleName() + ".getVolume() ==> " + vol);
    return vol;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
