package uebung;

public interface IGermanNavigation {

  // distance to target in km
  double getDistanceToTarget();

  boolean hasRadarWarning();

  void start();

}
