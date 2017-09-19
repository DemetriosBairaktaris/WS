package edu.luc.comp433.domain.order;

import edu.luc.comp433.domain.product.Product;

/**
 * Lays out the needs for an order detail.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface OrderDetail {

  /**
   * Retrieves the detail's id.
   * 
   * @return double
   */
  public double getId();

  /**
   * Retrieves the product.
   * 
   * @return Product
   */
  public Product getProduct();

  /**
   * Retrieves the number of items wanted.
   * 
   * @return long
   */
  public long getQuantity();

  /**
   * Retrieves status of item fulfillment.
   * 
   * @return String
   */
  public String getStatus();

  /**
   * Sets the id for the detail.
   * 
   * @param id
   *          double
   */
  public void setId(double id);

  /**
   * Sets the product for the detail.
   * 
   * @param product
   *          Product
   */
  public void setProduct(Product product);

  /**
   * Sets the number of products wanted.
   * 
   * @param quantity
   *          long
   */
  public void setQuantity(long quantity);

  /**
   * Sets the status of the detail.
   * 
   * @param status
   *          String
   */
  public void setStatus(String status);
}
