package edu.luc.comp433.domain.customer;

import edu.luc.comp433.dal.DatabaseAccess;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * This class manages the interactions with customer objects.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface CustomerManager {

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
   * @param expiration
   *          Date
   * @param password
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean createCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, Date expiration) throws SQLException;

  /**
   * Deletes a customer profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean deleteCustomer(String userName) throws SQLException;

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
   * @param expiration
   *          Date
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePayment(String userName, String cardName, String cardNumber, String cvv, Date expiration)
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

  /**
   * Updates a customer.
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
   * @param expiration
   *          String
   * @param password
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws ParseException
   *           thrown by Date parser
   */
  public boolean updateCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException;
}
