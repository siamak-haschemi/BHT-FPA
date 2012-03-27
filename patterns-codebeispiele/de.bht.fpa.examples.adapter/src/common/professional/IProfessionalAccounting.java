package common.professional;

public interface IProfessionalAccounting {

  void addOrder(IOrder order);

  double returnDailyBalance();

  boolean sendToTaxOffice();

  void printMonthReport();
}
