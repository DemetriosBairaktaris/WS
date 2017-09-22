package edu.luc.comp433.domain.order;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public interface OrderManager {
  
  public void setDatabase(DatabaseAccess database);
  
  public DatabaseAccess getDatabase();

  public int createOrder(String customer);

  public boolean createOrderDetail(int orderId, Product product, long quantity);

  public boolean fulfillOrder(int orderId);

  public boolean shipOrder(int orderId);

  public boolean cancelOrder(int orderId);

  public Order getOrder(int orderId);

  public OrderDetail getOrderDetail(int orderId, String productName);
}
