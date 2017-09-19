package edu.luc.comp433.domain.partner;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

import java.util.List;

public interface PartnerProfile {
  public void addOrder(Order order);

  public void addProduct(Product product);

  public String getAddress();

  public String getName();

  public List<Order> getOrders();

  public String getPhone();

  public List<Product> getProducts();

  public String getUserName();

  public void removeOrder(Order order);

  public void removeProduct(Product product);

  public void setAddress(String address);

  public void setName(String name);

  public void setOrders(List<Order> orders);

  public void setPhone(String phone);

  public void setProducts(List<Product> products);

  public void setUserName(String userName);
}
