package common.professional;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalAccounting implements IProfessionalAccounting {

  List<IOrder> orders = new ArrayList<IOrder>();

  @Override
  public void addOrder(IOrder order) {
    System.out.println(this.getClass().getName() + " adding order");
    orders.add(order);
  }

  @Override
  public double returnDailyBalance() {
    System.out.println(this.getClass().getName() + " computing daily balance");
    double balance = 0;
    for (IOrder order : orders) {
      balance += order.getPrice();
    }
    return balance;
  }

  @Override
  public boolean sendToTaxOffice() {
    System.out.println(this.getClass().getName() + " transmitting VAT");
    return true;
  }

  @Override
  public void printMonthReport() {
    System.out.println(this.getClass().getName() + " printing the report for the month...");
  }
}
