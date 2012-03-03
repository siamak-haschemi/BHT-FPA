package de.bht.fpa.icashbox.model;

public class Coffee implements Product {

  @Override
  public float getPrice() {
    return 1.25F;
  }

  @Override
  public String getDescription() {
    return "Coffee";
  }

  @Override
  public String toString() {
    return getDescription();
  }

}
