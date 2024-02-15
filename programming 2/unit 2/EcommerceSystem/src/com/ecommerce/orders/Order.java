package com.ecommerce.orders;

import java.util.List;

import com.ecommerce.Customer;
import com.ecommerce.Product;

public class Order {
  private String orderID;
  private Customer customer;
  private List<Product> products;
  private double orderTotal;

  public Order(String orderID, Customer customer, List<Product> products) {
    this.orderID = orderID;
    this.customer = customer;
    this.products = products;
    this.orderTotal = customer.calculateTotalCost();
  }

  public String getOrderID() {
    return orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public double getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(double orderTotal) {
    this.orderTotal = orderTotal;
  }

  public String generateOrderSummary() {
    return "Order ID: " + orderID + "\nCustomer: " + customer.getName() + "\nTotal: $" + orderTotal;
  }
}
