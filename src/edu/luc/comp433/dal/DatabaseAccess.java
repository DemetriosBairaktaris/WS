package edu.luc.comp433.dal;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

/**
 * Interface for database access.
 * 
 * @author Demetrios and Thaddeus
 *
 */
public interface DatabaseAccess {

  /**
   * Inserts an order and returns the order ID.
   * 
   * @param order
   *          Order
   * @return integer
   * @throws SQLException
   *           thrown by DB
   */
  public int insertOrder(Order order) throws SQLException;

  /**
   * Updates an order.
   * 
   * @param order
   *          Order
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateOrder(Order order) throws SQLException;

  /**
   * Retrieves an order.
   * 
   * @param orderId
   *          double
   * @return Order
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public Order getOrder(double orderId) throws SQLException, Exception;

  /**
   * Deletes an order.
   * 
   * @param order
   *          Order
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deleteOrder(Order order) throws SQLException;

  /**
   * Get orders for a partner.
   * 
   * @param userName
   *          String
   * @return List
   * @throws Exception
   *           thrown by DB
   */
  public List<Order> getOrdersFromPartner(String userName) throws Exception;

  /**
   * Inserts a partner profile.
   * 
   * @param profile
   *          PartnerProfile
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean insertPartner(PartnerProfile profile) throws SQLException, Exception;

  /**
   * Updates a partner profile.
   * 
   * @param profile
   *          PartnerProfile
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updatePartner(PartnerProfile profile) throws SQLException, Exception;

  /**
   * Deletes a partner.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deletePartner(String userName) throws SQLException;

  /**
   * Retrieves a partner profile.
   * 
   * @param userName
   *          String
   * @return PartnerProfile
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public PartnerProfile getPartnerProfile(String userName) throws Exception, SQLException;

  /**
   * Inserts a product.
   * 
   * @param product
   *          Product
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean insertProduct(Product product) throws SQLException;

  /**
   * Updates a product.
   * 
   * @param product
   *          Product
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateProduct(Product product) throws SQLException;

  /**
   * Get product for a specific partner.
   * 
   * @param productName
   *          String
   * @param profile
   *          PartnerProfile
   * @return Product
   * @throws SQLException
   *           thrown by DB
   */
  public Product getProductFromPartner(String productName, PartnerProfile profile) throws SQLException;

  /**
   * Retrieves a product.
   * 
   * @param productName
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<Product> getProduct(String productName) throws SQLException, Exception;

  /**
   * Deletes a product.
   * 
   * @param product
   *          Product
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deleteProduct(Product product) throws SQLException;

  /**
   * Retrieves all products from a partner.
   * 
   * @param companyUserName
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<Product> getAllProductsFromPartner(String companyUserName) throws SQLException, Exception;

  /**
   * Inserts a customer profile.
   * 
   * @param customer
   *          Customer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean insertCustomer(Customer customer) throws SQLException;

  /**
   * Updates a customer profile.
   * 
   * @param customer
   *          Customer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateCustomer(Customer customer) throws SQLException;

  /**
   * Deletes a customer.
   * 
   * @param customer
   *          Customer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deleteCustomer(Customer customer) throws SQLException;

  /**
   * Retrieves a customer profile.
   * 
   * @param userName
   *          String
   * @return Customer
   * @throws SQLException
   *           thrown by DB
   */
  public Customer getCustomer(String userName) throws SQLException;

  /**
   * Deletes a customer using a user name.
   * 
   * @param username
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean deleteCustomer(String username) throws SQLException;

  /**
   * General DAL
   */
  public void closeConnections();
}