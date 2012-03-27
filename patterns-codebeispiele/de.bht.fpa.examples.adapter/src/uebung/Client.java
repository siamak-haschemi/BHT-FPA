package uebung;

public class Client {

  public static void main(String args[]) {

    IGermanNavigation navigation = new GermanNavigation();

    double distance = navigation.getDistanceToTarget();
    boolean radarWarning = navigation.hasRadarWarning();
    System.out.println("distance=" + distance);
    System.out.println("radarWarning=" + radarWarning);
    navigation.start();

    IGermanNavigation navigationGB = new BritishNavigationAdapter(new BritishNavigation());
    double distanceGB = navigationGB.getDistanceToTarget();
    boolean radarWarningGB = navigationGB.hasRadarWarning();
    System.out.println("distanceGB=" + distanceGB);
    System.out.println("radarWarningGB=" + radarWarningGB);
    navigationGB.start();

  }
}
