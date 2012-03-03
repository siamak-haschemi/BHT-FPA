package v1;

import common.professional.ProfessionalAccounting;
import common.selfmade.ISelfMadeAccounting;

public class Client {

  /**
   * @param args
   */
  public static void main(String[] args) {
    ISelfMadeAccounting accounting = new ProfessionalAccountingAdapter(new ProfessionalAccounting());

    // Hier geht der Client los
    accounting.addOrder(2.94d);
    accounting.addOrder(15.98d);
    accounting.addOrder(5.50d);

    double dailyBalance = accounting.getDailyBalance();
    System.out.println("Tagesumsatz: " + dailyBalance);

    boolean success = accounting.sendToTaxOffice();
    System.out.println("Übertragung erfolgreich " + success);

  }

}
