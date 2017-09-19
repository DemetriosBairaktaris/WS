package edu.luc.comp433.domain.partner;

import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

/**
 * Lays out the needs of the partner manager.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface PartnerManager {

  /**
   * Adds a product to a partner.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @param desc
   *          String
   * @param cost
   *          double
   * @param stock
   *          long
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean addProduct(String userName, String name, String desc, double cost, long stock)
      throws Exception;

  /**
   * Creates a partner profile.
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
   */
  public boolean create(String userName, String name, String address, String phone);

  /**
   * Deletes a partner profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   */
  public boolean delete(String userName);

  /**
   * Retrieves the database access object.
   * 
   * @return DatabaseAccess
   */
  public DatabaseAccess getDatabase();

  /**
   * Retrieves a list of orders for a partner profile.
   * 
   * @param userName
   *          String
   * @return List of type Order
   * @throws Exception
   *           errors if database not ready
   */
  public List<Order> getOrders(String userName) throws Exception;

  /**
   * Retrieves a specific partner profile.
   * 
   * @param userName
   *          String
   * @return PartnerProfile
   */
  public PartnerProfile getPartnerProfile(String userName);

  /**
   * Searches for a specific product among all partners.
   * 
   * @param name
   *          String
   * @return Product
   */
  public Product getProduct(String name);

  /**
   * Removes a product from a partner profile.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean removeProduct(String userName, String name) throws Exception;

  /**
   * Sets the database.
   * 
   * @param database
   *          DatabaseAccess
   */
  public void setDatabase(DatabaseAccess database);

  /**
   * Updates a partner's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   */
  public boolean updateAddress(String userName, String address);

  /**
   * Updates a partner's product's cost.
   * 
   * @param userName
   *          String
   * @param cost
   *          double
   * @param name
   *          String
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean updateCost(String userName, double cost, String name) throws Exception;

  /**
   * Updates a partner's product's description.
   * 
   * @param userName
   *          String
   * @param desc
   *          String
   * @param name
   *          String
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean updateDescription(String userName, String desc, String name) throws Exception;

  /**
   * Updates a partner's name.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @return true if successful
   */
  public boolean updateName(String userName, String name);

  /**
   * Updates the list of orders in a partner profile.
   * 
   * @param userName
   *          String
   * @param orders
   *          List of type order
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean updateOrders(String userName, List<Order> orders) throws Exception;

  /**
   * Updates a partner's phone number.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   */
  public boolean updatePhone(String userName, String phone);

  /**
   * Updates a partner's product's available stock number.
   * 
   * @param userName
   *          String
   * @param stock
   *          long
   * @param name
   *          String
   * @return true if successful
   * @throws Exception
   *           errors if database not ready
   */
  public boolean updateStock(String userName, long stock, String name) throws Exception;
}
