package common.selfmade;

public interface ISelfMadeAccounting {

  void addOrder(double price);

  double getDailyBalance();

  boolean sendToTaxOffice();
}
