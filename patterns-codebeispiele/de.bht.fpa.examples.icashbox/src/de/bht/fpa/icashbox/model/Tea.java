package de.bht.fpa.icashbox.model;

public class Tea implements Product {

  @Override
  public float getPrice() {
    return 0.20F;
  }

  @Override
  public String getDescription() {
    return "Tea";
  }

  @Override
  public String toString() {
    return getDescription();
  }
}
