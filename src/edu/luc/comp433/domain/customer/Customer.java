package edu.luc.comp433.domain.customer;

import edu.luc.comp433.domain.order.Order;

import java.util.List;

public interface Customer {
  public void addOrder(Order order);

  public String getAddress();

  public String getFirstName();

  public String getLastName();

  public List<Order> getOrders();

  public Payment getPayment();

  public String getPhone();

  public String getUserName();

  public void removeOrder(Order order);

  public void setAddress(String address);

  public void setFirstName(String firstName);

  public void setLastName(String lastName);

  public void setOrders(List<Order> orders);

  public void setPayment(Payment payment);

  public void setPhone(String phone);

  public void setUserName(String userName);
}
