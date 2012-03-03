package de.bht.fpa.icashbox.model;

public class Milk extends Extra {

  public Milk(Product product) {
    super(product);
  }

  @Override
  public float getPrice() {
    return getProduct().getPrice() + 0.10F;
  }

  @Override
  public String getDescription() {
    return getProduct().getDescription() + ", Milk";
  }

}
