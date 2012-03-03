package de.bht.fpa.icashbox.model;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Product product = new Milk(new Sugar(new Coffee()));
    System.out.println(product.getDescription() + " costs " + product.getPrice() + " € in Prenzlauer Berg.");
  }

}
