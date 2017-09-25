package edu.luc.comp433.domain.order;

import java.sql.SQLException;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

/**
 * Interface for the Order Manager.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface OrderManager {

  /**
   * Sets the database object.
   * 
   * @param database
   *          DatabaseAccess
   */
  public void setDatabase(DatabaseAccess database);

  /**
   * Retrieves the database object.
   * 
   * @return DatabaseAccess
   */
  public DatabaseAccess getDatabase();

  /**
   * Creates an order for the customer and returns the order id.
   * 
   * @param customer
   *          String
   * @return integer
   * @throws SQLException
   *           errors if database not ready.
   */
  public int createOrder(String customer) throws SQLException;

  /**
   * Creates an order detail and adds it to an order.
   * 
   * @param orderId
   *          integer
   * @param product
   *          Product
   * @param quantity
   *          long
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean createOrderDetail(int orderId, Product product, long quantity)
      throws SQLException, Exception;

  /**
   * Fulfills the given order.
   * 
   * @param orderId
   *          integer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean fulfillOrder(int orderId) throws SQLException, Exception;

  /**
   * Sets the status to shipped for an order.
   * 
   * @param orderId
   *          integer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean shipOrder(int orderId) throws SQLException, Exception;

  /**
   * Cancels and removes an order.
   * 
   * @param orderId
   *          integer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean cancelOrder(int orderId) throws SQLException, Exception;

  /**
   * Retrieves a given order.
   * 
   * @param orderId
   *          integer
   * @return Order
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public Order getOrder(int orderId) throws SQLException, Exception;

  /**
   * Retrieves an order's details by product name.
   * 
   * @param orderId
   *          integer
   * @param productName
   *          String
   * @return OrderDetail
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public OrderDetail getOrderDetail(int orderId, String productName) throws SQLException, Exception;
}
