package edu.luc.comp433.domain.product;

import java.util.List;

/**
 * Lays out the needs of the Product class.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Product {

  /**
   * Retrieves product cost.
   * 
   * @return double
   */
  public double getCost();

  /**
   * Retrieves the product description.
   * 
   * @return String
   */
  public String getDesc();

  /**
   * Retrieves the product name.
   * 
   * @return String
   */
  public String getName();

  /**
   * Retrieves the amount of available products.
   * 
   * @return long
   */
  public long getStock();

  /**
   * Sets the product's cost.
   * 
   * @param cost
   *          double
   */
  public void setCost(double cost);

  /**
   * Sets the product's description.
   * 
   * @param desc
   *          String
   */
  public void setDesc(String desc);

  /**
   * Sets the products name.
   * 
   * @param name
   *          String
   */
  public void setName(String name);

  /**
   * Sets the number of available stock for a product.
   * 
   * @param stock
   *          long
   */
  public void setStock(long stock);
  
  public void setReviews(List<Review> reviews);
  public List<Review> getReviews();
  public void setCompanyName(String companyName);
  public String getCompanyName();
}
