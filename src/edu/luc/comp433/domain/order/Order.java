package edu.luc.comp433.domain.order;

import java.util.Date;
import java.util.List;

/**
 * Lays out the needs for an order class.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Order {

  /**
   * Adds an OrderDetail to the list for this order.
   * 
   * @param detail
   *          OrderDetail
   */
  public void addOrderDetail(OrderDetail detail);

  /**
   * Retrieves the list of OrderDetails.
   * 
   * @return List of type OrderDetail
   */
  public List<OrderDetail> getDetails();

  /**
   * Retrieves the status of this order.
   * 
   * @return String
   */
  public String getStatus();

  /**
   * Retrieves the time stamp of when the order was created.
   * 
   * @return Date
   */
  public Date getTimestamp();

  /**
   * Removes the OrderDetail when order is updated with a removal.
   * 
   * @param detail
   *          OrderDetail
   */
  public void removeOrderDetail(OrderDetail detail);

  /**
   * Sets the list of OrderDetails for this order.
   * 
   * @param details
   *          List of type OrderDetail
   */
  public void setDetails(List<OrderDetail> details);

  /**
   * Sets the order's status. Only can be changed once all OrderDetails are fulfilled.
   * 
   * @param status
   *          String
   */
  public void setStatus(String status);

  /**
   * Sets the time stamp when the order is created.
   */
  public void setTimestamp();

  public void setCustomer(String userName);

  public String getCustomer();
  
  public void setOrderId(int orderId);
  
  public int getOrderId();
}
