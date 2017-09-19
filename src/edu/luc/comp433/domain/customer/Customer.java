package edu.luc.comp433.domain.customer;

import java.util.List;

import edu.luc.comp433.domain.order.Order;

public interface Customer {
  public void setUserName(String userName);
  public String getUserName();
  public void setFirstName(String firstName);
  public String getFirstName();
  public void setLastName(String lastName);
  public String getLastName();
  public void setAddress(String address);
  public String getAddress();
  public void setPhone(String phone);
  public String getPhone();
  public void setPayment(Payment payment);
  public Payment getPayment();
  public void setOrders(List<Order> orders);
  public List<Order> getOrders();
  public void addOrder(Order order);
  public void removeOrder(Order order);
}
