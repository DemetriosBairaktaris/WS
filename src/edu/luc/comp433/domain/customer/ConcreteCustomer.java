package edu.luc.comp433.domain.customer;

import edu.luc.comp433.domain.order.Order;

import java.util.List;

public class ConcreteCustomer implements Customer {

  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private Payment payment;
  private List<Order> orders;

  public ConcreteCustomer() {
  }

  @Override
  public void addOrder(Order order) {
    orders.add(order);
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public List<Order> getOrders() {
    return orders;
  }

  @Override
  public Payment getPayment() {
    return payment;
  }

  @Override
  public String getPhone() {
    return phone;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public void removeOrder(Order order) {
    orders.remove(order);
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  @Override
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public void setUserName(String userName) {
    this.userName = userName;
  }
}
