package edu.luc.comp433.domain.product;

/**
 * Interface for the review object.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Review {

  /**
   * Retrieves a review.
   * 
   * @return String
   */
  public String getReview();

  /**
   * Sets the review.
   * 
   * @param review
   *          String
   */
  public void setReview(String review);

  /**
   * Retrieves the rating.
   * 
   * @return integer
   */
  public int getRating();

  /**
   * Sets the rating.
   * 
   * @param rating
   *          integer
   */
  public void setRating(int rating);

}
