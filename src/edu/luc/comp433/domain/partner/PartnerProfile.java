package edu.luc.comp433.domain.partner;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

import java.util.List;

/**
 * Lays out the needs for a partner profile.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface PartnerProfile {

  /**
   * Adds an Order to the partner's list of orders.
   * 
   * @param order
   *          Order
   */
  public void addOrder(Order order);

  /**
   * Adds a product to the partner's list of orders.
   * 
   * @param product
   *          Product
   */
  public void addProduct(Product product);

  /**
   * Retrieves the partner's address.
   * 
   * @return String
   */
  public String getAddress();

  /**
   * Retrieves the partner's name.
   * 
   * @return String
   */
  public String getName();

  /**
   * Retrieves the list of Orders.
   * 
   * @return List of type Order
   */
  public List<Order> getOrders();

  /**
   * Retrieves the partner's phone number.
   * 
   * @return String
   */
  public String getPhone();

  /**
   * Retrieves the list of products sold by this partner.
   * 
   * @return List of type product
   */
  public List<Product> getProducts();

  /**
   * Retrieves the partner's user name.
   * 
   * @return String
   */
  public String getUserName();

  /**
   * Removes an order for this partner.
   * 
   * @param order
   *          Order
   */
  public void removeOrder(Order order);

  /**
   * Removes a product for this partner.
   * 
   * @param product
   *          Product
   */
  public void removeProduct(Product product);

  /**
   * Sets this partner's address.
   * 
   * @param address
   *          String
   */
  public void setAddress(String address);

  /**
   * Sets this partner's name.
   * 
   * @param name
   *          String
   */
  public void setName(String name);

  /**
   * Sets this partner's list of Orders.
   * 
   * @param orders
   *          List of type Order
   */
  public void setOrders(List<Order> orders);

  /**
   * Sets this partner's phone number.
   * 
   * @param phone
   *          String
   */
  public void setPhone(String phone);

  /**
   * Sets the partner's list of products.
   * 
   * @param products
   *          List of type Product
   */
  public void setProducts(List<Product> products);

  /**
   * Sets the partner's user name.
   * 
   * @param userName
   *          String
   */
  public void setUserName(String userName);
}
