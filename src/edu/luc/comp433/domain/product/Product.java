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

  /**
   * Sets the list of reviews.
   * 
   * @param reviews
   *          List
   */
  public void setReviews(List<Review> reviews);

  /**
   * Retrieves all the reviews.
   * 
   * @return List
   */
  public List<Review> getReviews();

  /**
   * Sets the company owner of the product.
   * 
   * @param companyUserName
   *          String
   */
  public void setCompanyUserName(String companyUserName);

  /**
   * Retrieves the owner of the product.
   * 
   * @return String
   */
  public String getCompanyUserName();
}
