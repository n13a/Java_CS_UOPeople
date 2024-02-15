package com.ecommerce;

public class Product {
  private String productID;
  private String name;
  private double price;

  public Product(String productID, String name, double price) {
    this.productID = productID;
    this.name = name;
    this.price = price;
  }

  // getters and setters
  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
