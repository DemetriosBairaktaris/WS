package edu.luc.comp433.domain.customer;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;

import java.sql.SQLException;

public interface CustomerManager {
  public boolean addOrderDetail(String userName, double orderId, String productName, long quantity)
      throws SQLException;

  public boolean cancelOrder(String userName, double orderId) throws SQLException;

  public boolean create(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv) throws SQLException;

  public boolean createOrder(String userName) throws SQLException;

  public boolean delete(String userName) throws SQLException;

  public Customer getCustomer(String userName) throws SQLException;

  public DatabaseAccess getDatabase();

  public Order getOrder(String userName, double orderId) throws SQLException;

  public void setDatabase(DatabaseAccess database);

  public boolean updateAddress(String userName, String address) throws SQLException;

  public boolean updateName(String userName, String firstName, String lastName) throws SQLException;

  public boolean updateOrderQuantity(String userName, double orderId, String productName,
      long quantity) throws SQLException;

  public boolean updatePayment(String userName, String cardName, String cardNumber, String cvv)
      throws SQLException;

  public boolean updatePhone(String userName, String phone) throws SQLException;
}
