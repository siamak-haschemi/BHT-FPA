package uebung;

public class Client {

	public static void main(String args[]) {

		IGermanNavigation navigation = new GermanNavigation();

		double distance = navigation.getDistanceToTarget();
		boolean radarWarning = navigation.hasRadarWarning();
		navigation.start();

		IGermanNavigation navigationGB = new BritishNavigationAdapter(
				new BritishNavigation());
		double distanceGB = navigationGB.getDistanceToTarget();
		boolean radarWarningGB = navigationGB.hasRadarWarning();
		navigationGB.start();

	}
}
