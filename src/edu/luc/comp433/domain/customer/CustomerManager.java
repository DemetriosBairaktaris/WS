package edu.luc.comp433.domain.customer;

import java.sql.SQLException;

import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

public interface CustomerManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean create(String userName, String firstName, String lastName, String address, String phone,
      String cardName, String cardNumber, String CVV) throws SQLException;
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException;
  public boolean updateAddress(String userName, String address) throws SQLException;
  public boolean updatePhone(String userName, String phone) throws SQLException;
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException;
  public boolean delete(String userName) throws SQLException;
  public Customer getCustomer(String userName) throws SQLException;
  public Order getOrder(String userName, double orderID) throws SQLException;
  public boolean createOrder(String userName) throws SQLException;
  public boolean addOrderDetail(String userName, double orderID, String productName, long quantity) throws SQLException;
  public boolean updateOrderQuantity(String userName, double orderID, String productName, long quantity) throws SQLException;
  public boolean cancelOrder(String userName, double orderID) throws SQLException;
}
