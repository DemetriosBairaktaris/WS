package edu.luc.comp433.domain.customer;

import java.util.List;

import edu.luc.comp433.domain.order.Order;

public class ConcreteCustomer implements Customer {

  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private Payment payment;
  private List<Order> orders;
  
  
  public ConcreteCustomer() {};
  
  @Override
  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String getPhone() {
    return phone;
  }

  @Override
  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  @Override
  public Payment getPayment() {
    return payment;
  }

  @Override
  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public List<Order> getOrders() {
    return orders;
  }

  @Override
  public void addOrder(Order order) {
    orders.add(order);
  }

  @Override
  public void removeOrder(Order order) {
    orders.remove(order);
  }
}
