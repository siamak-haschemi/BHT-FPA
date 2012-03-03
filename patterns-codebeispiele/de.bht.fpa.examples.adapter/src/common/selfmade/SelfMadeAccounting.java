package common.selfmade;

import java.util.ArrayList;
import java.util.List;

public class SelfMadeAccounting implements ISelfMadeAccounting {

	List<Double> orders = new ArrayList<Double>();

	@Override
	public void addOrder(double price) {
		System.out.println(this.getClass().getName() + " adding order");
		orders.add(price);
	}

	@Override
	public double getDailyBalance() {
		System.out.println(this.getClass().getName() + " computing daily balance");
		double balance = 0;
		for (Double orderPrice : orders) {
			balance += orderPrice;
		}
		return balance;
	}

	@Override
	public boolean sendToTaxOffice() {
		System.out.println(this.getClass().getName() + " transmitting VAT");
		return true;
	}
}
