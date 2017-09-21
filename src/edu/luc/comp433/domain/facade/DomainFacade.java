package edu.luc.comp433.domain.facade;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.partner.PartnerManager;

import java.sql.SQLException;

/**
 * This class sets up the facade between the domain layer and the service layer.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface DomainFacade {

  /**
   * Creates an order for the specified customer and quantity of product.
   * 
   * @param userName
   *          String
   * @param productName
   *          String
   * @param quantity
   *          long
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean acceptBuyOrder(String userName, String productName, long quantity)
      throws SQLException;

  /**
   * Accepts a payment on an order.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean acceptPayment(String userName, double orderId) throws SQLException;

  /**
   * Adds a product for a partner company.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @param desc
   *          String
   * @param cost
   *          String
   * @param stock
   *          long
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean addProduct(String userName, String name, String desc, double cost, long stock)
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
   * Retrieves the customer manager.
   * 
   * @return CustomerManager
   */
  public CustomerManager getCustomerManager();

  /**
   * Retrieves the status of an order and checks if fulfilled.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return true if fulfilled, false otherwise
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean getOrderFulfillment(String userName, double orderId) throws SQLException;

  /**
   * Retrieves a list of orders.
   * 
   * @param userName
   *          String
   * @return list of orders in String format
   * @throws SQLException
   *           errors if database not ready
   */
  public String getOrders(String userName) throws SQLException;

  /**
   * Retrieves the status of a specific order
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return String
   * @throws SQLException
   *           errors if database not ready
   */
  public String getOrderStatus(String userName, double orderId) throws SQLException;

  /**
   * Retrieves the partner manager.
   * 
   * @return PartnerManager
   */
  public PartnerManager getPartnerManager();

  /**
   * Retrieves the information for a specific product in String format.
   * 
   * @param productName
   *          String
   * @return String
   * @throws SQLException
   *           errors if database not ready
   */
  public String getProduct(String productName) throws SQLException;

  /**
   * Creates a new customer
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
  public boolean registerCustomer(String userName, String firstName, String lastName,
      String address, String phone, String cardName, String cardNumber, String cvv)
      throws SQLException;

  /**
   * Creates a partner company profile.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @param address
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean registerPartner(String userName, String name, String address, String phone)
      throws SQLException;

  /**
   * Sets the customer manager.
   * 
   * @param customer
   *          CustomerManager
   */
  public void setCustomerManager(CustomerManager customer);

  /**
   * Sets the partner manager.
   * 
   * @param partners
   *          PartnerManager
   */
  public void setPartnerManager(PartnerManager partners);

  /**
   * Updates the status of an order to shipped.
   * 
   * @param userName
   *          String
   * @param orderId
   *          double
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean shipOrder(String userName, double orderId) throws SQLException;

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
  public boolean updateCustomerAddress(String userName, String address) throws SQLException;

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
  public boolean updateCustomerName(String userName, String firstName, String lastName)
      throws SQLException;

  /**
   * Updates the cusomter's phone.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updateConsumerPhone(String userName, String phone) throws SQLException;

  /**
   * Updates the partner's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePartnerAddress(String userName, String address) throws SQLException;

  /**
   * Updates the partner's name.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePartnerName(String userName, String name) throws SQLException;

  /**
   * Updates the partner's phone.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           errors if database not ready
   */
  public boolean updatePartnerPhone(String userName, String phone) throws SQLException;

  /**
   * Updates the customer's payment information.
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
}
