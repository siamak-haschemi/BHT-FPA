package de.bht.fpa.icashbox.model;

public class Sugar extends Extra {

  public Sugar(Product product) {
    super(product);
  }

  @Override
  public float getPrice() {
    return getProduct().getPrice() + 0.05F;
  }

  @Override
  public String getDescription() {
    return getProduct().getDescription() + ", Sugar";
  }

}
