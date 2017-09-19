package edu.luc.comp433.domain.partner;

import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

public interface PartnerProfile {
  public void setUserName(String userName);
  public String getUserName();
  public String getName();
  public void setName(String name);
  public void setAddress(String address);
  public String getAddress();
  public void setPhone(String phone);
  public String getPhone();
  public void setOrders(List<Order> orders);
  public List<Order> getOrders();
  public void addOrder(Order order);
  public void removeOrder(Order order);
  public void setProducts(List<Product> products);
  public List<Product> getProducts();
  public void addProduct(Product product);
  public void removeProduct(Product product);
}
