package edu.luc.comp433.domain.product;

public class ConcreteReview implements Review {

  private String review;
  private int rating;

  public ConcreteReview() {
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }
}
