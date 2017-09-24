package edu.luc.comp433.domain.order;

import java.sql.SQLException;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public interface OrderManager {

  public void setDatabase(DatabaseAccess database);

  public DatabaseAccess getDatabase();

  public int createOrder(String customer) throws SQLException;

  public boolean createOrderDetail(int orderId, Product product, long quantity)
      throws SQLException, Exception;

  public boolean fulfillOrder(int orderId) throws SQLException, Exception;

  public boolean shipOrder(int orderId) throws SQLException, Exception;

  public boolean cancelOrder(int orderId) throws SQLException, Exception;

  public Order getOrder(int orderId) throws SQLException, Exception;

  public OrderDetail getOrderDetail(int orderId, String productName) throws SQLException, Exception;
}
