package de.bht.fpa.icashbox.model;

public abstract class Extra implements Product {
  private final Product product;

  public Extra(Product product) {
    this.product = product;
  }

  public Product getProduct() {
    return product;
  }

  @Override
  public String toString() {
    return getDescription();
  }
}
