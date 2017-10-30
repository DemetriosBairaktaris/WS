package edu.luc.comp433.domain.product;

public class ConcreteReview implements Review {

  private String review;
  private int rating;

  public ConcreteReview() {
  }

  @Override
  public String getReview() {
    return review;
  }

  @Override
  public void setReview(String review) {
    this.review = review;
  }

  @Override
  public int getRating() {
    return rating;
  }

  @Override
  public void setRating(int rating) {
    this.rating = rating;
  }
}
