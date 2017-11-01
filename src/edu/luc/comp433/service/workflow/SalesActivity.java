package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.product.Product;
import edu.luc.comp433.domain.product.ProductManager;
import edu.luc.comp433.domain.product.Review;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ReviewRepresentation;

/**
 * Activity for both orders and products.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface SalesActivity {

  /**
   * Sets the order manager.
   * 
   * @param orders
   *          OrderManager
   */
  public void setOrders(OrderManager orders);

  /**
   * Retrieves the order manager.
   * 
   * @return OrderManager
   */
  public OrderManager getOrders();

  /**
   * Sets the product manager.
   * 
   * @param products
   *          ProductManager
   */
  public void setProducts(ProductManager products);

  /**
   * Retrieves the product manager.
   * 
   * @return ProductManager
   */
  public ProductManager getProducts();

  /**
   * Searches for products by name.
   * 
   * @param productName
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<ProductRepresentation> searchProduct(String productName) throws SQLException, Exception;

  /**
   * Checks if a product is available.
   * 
   * @param productName
   *          String
   * @return true if available
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean checkAvailability(String productName) throws SQLException, Exception;

  /**
   * Purchases a product.
   * 
   * @param customerName
   *          String
   * @param productName
   *          String
   * @param quantity
   *          long
   * @param orderId
   *          integer
   * @return integer
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public int buyProduct(String customerName, String productName, long quantity, int orderId)
      throws SQLException, Exception;

  /**
   * Changes the status of an order to fulfilled.
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
   * Cancels an order and refunds the money.
   * 
   * @param orderId
   *          integer
   * @return integer
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public int cancelOrder(int orderId) throws SQLException, Exception;

  /**
   * Ships an order.
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
   * Retrieves an order's status.
   * 
   * @param orderId
   *          integer
   * @return String
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public String getOrderStatus(int orderId) throws SQLException, Exception;

  /**
   * Adds a review to a specific product.
   * 
   * @param userName
   *          String
   * @param productName
   *          String
   * @param review
   *          String
   * @param rating
   *          integer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean addReview(String userName, String productName, String review, int rating)
      throws SQLException, Exception;

  /**
   * Accept a partner's product.
   * 
   * @param userName
   *          String
   * @param productName
   *          String
   * @param productDesc
   *          String
   * @param cost
   *          double
   * @param stock
   *          long
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc, double cost, long stock)
      throws SQLException, Exception;

  /**
   * Gets product from a partner.
   * 
   * @param productName
   *          String
   * @param partnerUserName
   *          String
   * @return Product Representation
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by other
   */
  public ProductRepresentation getProductFromPartner(String productName, String partnerUserName)
      throws SQLException, Exception;

  /**
   * Retrieves an order by its ID.
   * 
   * @param orderId
   *          integer
   * @return OrderRepresentation
   */
  public OrderRepresentation getOrderById(int orderId);

  /**
   * Retrieves reviews for a product.
   * 
   * @param productName
   *          String
   * @return List of ReviewRepresentation
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown for other
   */
  public List<ReviewRepresentation> getReviews(String productName) throws SQLException, Exception;

  /**
   * Method to assemble a review from below.
   * 
   * @param review
   *          Review to be assembled
   * @return completed ReviewRepresentation
   */
  public ReviewRepresentation assembleReviewToRepresentation(Review review);

  /**
   * Method to assemble an order from below.
   * 
   * @param order
   *          Order to be assembled
   * @return completed OrderRepresentation
   */
  public OrderRepresentation assembleOrderToRepresentation(Order order);

  /**
   * Method to assemble a product from below.
   * 
   * @param product
   *          Product to be assembled
   * @return completed ProductRepresentation
   */
  public ProductRepresentation assembleProductToRepresentation(Product product);

  /**
   * 
   * @param productName
   * @param review
   * @param rating
   * @throws Exception
   */
  void insertReview(String productName, String review, int rating) throws Exception;
}
