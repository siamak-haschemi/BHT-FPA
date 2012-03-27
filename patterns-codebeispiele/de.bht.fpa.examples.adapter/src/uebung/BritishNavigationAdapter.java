package uebung;

public class BritishNavigationAdapter implements IGermanNavigation {

  private static final double MILES_TO_KM = 1.6d;
  private IBritishNavigation britishNavigation;

  public BritishNavigationAdapter(IBritishNavigation britishNavigation) {
    this.britishNavigation = britishNavigation;
  }

  @Override
  public double getDistanceToTarget() {
    return this.britishNavigation.getDistanceToTarget() * MILES_TO_KM;
  }

  @Override
  public boolean hasRadarWarning() {
    throw new UnsupportedOperationException("No radar warning system allowed in GB");
  }

  @Override
  public void start() {
    this.britishNavigation.go();
  }
}
