package edu.luc.comp433.domain.customer;

import edu.luc.comp433.domain.order.Order;

import java.util.List;

/**
 * Details the set up of a customer object.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Customer {
  /**
   * Adds an order to the customers list of orders.
   * 
   * @param order
   *          created order object
   */
  public void addOrder(Order order);

  /**
   * Retrieves the customer's address.
   * 
   * @return String
   */
  public String getAddress();

  /**
   * Retrieves the customer's first name.
   * 
   * @return String
   */
  public String getFirstName();

  /**
   * Retrieves the customer's last name.
   * 
   * @return String
   */
  public String getLastName();

  /**
   * Retrieves all the customer's orders.
   * 
   * @return A list of type Order
   */
  public List<Order> getOrders();

  /**
   * Retrieves the customer's payment information.
   * 
   * @return Payment
   */
  public Payment getPayment();

  /**
   * Retrieves the customer's phone number.
   * 
   * @return String
   */
  public String getPhone();

  /**
   * Retrieves the customer's user name.
   * 
   * @return String
   */
  public String getUserName();

  /**
   * Removes a canceled order from the customer's order list.
   * 
   * @param order
   *          canceled Order
   */
  public void removeOrder(Order order);

  /**
   * Sets the customer's address.
   * 
   * @param address
   *          String
   */
  public void setAddress(String address);

  /**
   * Sets the customer's first name.
   * 
   * @param firstName
   *          String
   */
  public void setFirstName(String firstName);

  /**
   * Sets the customer's last name.
   * 
   * @param lastName
   *          String
   */
  public void setLastName(String lastName);

  /**
   * Sets the customer's list of orders.
   * 
   * @param orders
   *          List of type Order
   */
  public void setOrders(List<Order> orders);

  /**
   * Sets the customer's payment information.
   * 
   * @param payment
   *          Payment
   */
  public void setPayment(Payment payment);

  /**
   * Sets the customer's phone number.
   * 
   * @param phone
   *          String
   */
  public void setPhone(String phone);

  /**
   * Sets the customer's user name.
   * 
   * @param userName
   *          String
   */
  public void setUserName(String userName);
}
