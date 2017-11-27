package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.text.ParseException;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.service.representation.CustomerRepresentation;

/**
 * Interface for the customer activity methods.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface CustomerActivity {

  /**
   * Sets the customer manager.
   * 
   * @param customers
   *          CustomerManager
   */
  public void setCustomers(CustomerManager customers);

  /**
   * Retrieves the customer manager.
   * 
   * @return CustomerManager
   */
  public CustomerManager getCustomers();

  /**
   * Adds a customer profile to the system.
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
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws ParseException
   *           thrown by parsing the expiration
   */
  public boolean addCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException;

  /**
   * Checks if a customer profile is active.
   * 
   * @param userName
   *          String
   * @return true if profile exists.
   * @throws SQLException
   *           thrown by DB
   */
  public boolean checkCustomerStatus(String userName) throws SQLException;

  /**
   * Deletes a customer profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deleteCustomer(String userName) throws SQLException;

  /**
   * Updates a customer's name.
   * 
   * @param userName
   *          String
   * @param firstName
   *          String
   * @param lastName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateCustomerName(String userName, String firstName, String lastName) throws SQLException;

  /**
   * Updates a customer's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateCustomerAddress(String userName, String address) throws SQLException;

  /**
   * Update the customer's phone number.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateCustomerPhone(String userName, String phone) throws SQLException;

  /**
   * Updates the customer's payment info.
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
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws ParseException
   *           thrown by parsing expiration into date
   */
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException;

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
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws ParseException
   *           thrown by Date parser
   */
  public boolean updateCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException;

  /**
   * Retrieves the customer.
   * 
   * @param userName
   *          String
   * @return CustomerRepresentation
   * @throws SQLException
   *           thrown by DB
   */
  public CustomerRepresentation getCustomer(String userName) throws SQLException;
}
