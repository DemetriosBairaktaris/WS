package edu.luc.comp433.domain.order;

import java.util.Date;

import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public interface OrderManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean createOrder(double id, Date timestamp);
  public boolean createOrderDetail(double id, Product product, long quantity, String status, double orderID);
  public boolean addOrderDetail(double id, OrderDetail detail);
  public boolean updateOrder(String status, double id);
  public boolean updateOrderDetail(String status, double id, double orderID);
  public boolean updateOrderDetailQuantity(long quantity, double id, double orderID);
  public boolean removeOrderDetail(double id, double orderID);
  public boolean closeOrder(double id);
  public Order getOrder(double id);
}
