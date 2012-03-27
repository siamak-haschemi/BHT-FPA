package common.professional;

public class Order implements IOrder {

  private double price;

  public Order(double price) {
    this.price = price;
  }

  @Override
  public double getPrice() {
    return this.price;
  }

}
