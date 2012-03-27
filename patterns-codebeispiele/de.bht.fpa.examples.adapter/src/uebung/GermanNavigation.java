package uebung;

public class GermanNavigation implements IGermanNavigation {

  @Override
  public double getDistanceToTarget() {
    return 4811;
  }

  @Override
  public boolean hasRadarWarning() {
    return false;
  }

  @Override
  public void start() {
    System.out.println(this.getClass().getName() + " Start");
  }

}
