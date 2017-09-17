package edu.luc.comp433.domain.partner;

import java.util.List;

import edu.luc.comp433.domain.order.Order;

public class ConcretePartnerProfile implements PartnerProfile {

  private String userName;
  private String name;
  private String address;
  private String phone;
  private List<Order> orders;

  public ConcretePartnerProfile() {}

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setUserName(String userName) {
    this.userName = userName;
  }

    @Override
    public String getUserName() {
      return userName;
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
