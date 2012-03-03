package de.bht.fpa.icashbox.model;

import java.util.Vector;

public class Order {
  private final Vector<Product> products = new Vector<Product>();

  public void addProduct(Product product) {
    products.add(product);
  }

  public Product[] getProducts() {
    return products.toArray(new Product[products.size()]);
  }

  public void replaceProduct(Product product, Product product2) {
    int indexOf = products.indexOf(product);
    if (indexOf >= 0) {
      products.set(indexOf, product2);
    }
  }
}
