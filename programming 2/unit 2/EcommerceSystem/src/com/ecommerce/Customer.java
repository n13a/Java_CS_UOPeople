package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private String customerID;
  private String name;
  private List<Product> shoppingCart;

  public Customer(String customerID, String name) {
    this.customerID = customerID;
    this.name = name;
    this.shoppingCart = new ArrayList<>();
  }

  public String getCustomerID() {
    return customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getShoppingCart() {
    return shoppingCart;
  }

  public void addProductToCart(Product product) {
    if (!shoppingCart.contains(product)) {
      shoppingCart.add(product);
    }
  }

  public void removeProductFromCart(Product product) {
    shoppingCart.remove(product);
  }

  public double calculateTotalCost() {
    double total = 0;
    for (Product product : shoppingCart) {
      total += product.getPrice();
    }
    return total;
  }

  public boolean hasProductInCart(Product product) {
    return shoppingCart.contains(product);
  }
}
