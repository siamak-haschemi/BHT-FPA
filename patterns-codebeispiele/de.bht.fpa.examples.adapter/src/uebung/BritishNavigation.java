package uebung;

public class BritishNavigation implements IBritishNavigation {

	@Override
	public double getDistanceToTarget() {
		System.out.println(this.getClass().getName()
				+ " getDistanceToTarget in Miles");
		return 4711;
	}

	@Override
	public void go() {
		System.out.println(this.getClass().getName() + " Go");
	}

}
