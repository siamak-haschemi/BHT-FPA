package v1;

import common.professional.IProfessionalAccounting;
import common.professional.Order;
import common.selfmade.ISelfMadeAccounting;

public class ProfessionalAccountingAdapter implements ISelfMadeAccounting {

  private IProfessionalAccounting professionalAccounting = null;

  public ProfessionalAccountingAdapter(IProfessionalAccounting accounting) {
    this.professionalAccounting = accounting;
  }

  @Override
  public void addOrder(double price) {
    professionalAccounting.addOrder(new Order(price));
  }

  @Override
  public double getDailyBalance() {
    return professionalAccounting.returnDailyBalance();
  }

  @Override
  public boolean sendToTaxOffice() {
    return professionalAccounting.sendToTaxOffice();
  }

}
