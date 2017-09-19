package edu.luc.comp433.domain.customer;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;

import java.sql.SQLException;

/**
 * This class manages the interactions with customer objects.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface CustomerManager {

  /**
   * Adds an order detail to a specified customer's order.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @param productName
   *          String
   * @param quantity
   *          long
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean addOrderDetail(String userName, double orderId, String productName, long quantity)
      throws SQLException;

  /**
   * Cancels a customer's order.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean cancelOrder(String userName, double orderId) throws SQLException;

  /**
   * Creates a customer profile.
   * 
   * @param userName
   *          String
   * @param firstName
   *          String
   * @param lastName
   *          String
   * @param address
   *          String
   * @param phone
   *          String
   * @param cardName
   *          String
   * @param cardNumber
   *          String
   * @param cvv
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean create(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv) throws SQLException;

  /**
   * Creates an order for specified customer.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean createOrder(String userName) throws SQLException;

  /**
   * Deletes a customer profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean delete(String userName) throws SQLException;

  /**
   * Retrieves a customer profile.
   * 
   * @param userName
   *          String
   * @return A complete Customer profile
   * @throws SQLException
   *           errors if database not ready
   */
  public Customer getCustomer(String userName) throws SQLException;

  /**
   * Retrieves the database access object.
   * 
   * @return DatabaseAccess
   */
  public DatabaseAccess getDatabase();

  /**
   * Retrieves a specific order from a customer.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return the complete Order
   * @throws SQLException
   *           errors if database not ready
   */
  public Order getOrder(String userName, double orderId) throws SQLException;

  /**
   * Sets the database access object.
   * 
   * @param database
   *          DatabaseAccess
   */
  public void setDatabase(DatabaseAccess database);

  /**
   * Updates the customer's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updateAddress(String userName, String address) throws SQLException;

  /**
   * Updates the customer's name.
   * 
   * @param userName
   *          String
   * @param firstName
   *          String
   * @param lastName
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException;

  /**
   * Updates the quantity of a product in a customer's specific order.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @param productName
   *          String
   * @param quantity
   *          long
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updateOrderQuantity(String userName, double orderId, String productName,
      long quantity) throws SQLException;

  /**
   * Updates a customer's payment information.
   * 
   * @param userName
   *          String
   * @param cardName
   *          String
   * @param cardNumber
   *          String
   * @param cvv
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePayment(String userName, String cardName, String cardNumber, String cvv)
      throws SQLException;

  /**
   * Updates a customer's phone number.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePhone(String userName, String phone) throws SQLException;
}
