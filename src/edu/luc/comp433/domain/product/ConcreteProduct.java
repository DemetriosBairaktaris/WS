package edu.luc.comp433.domain.product;

import java.util.List;

public class ConcreteProduct implements Product {

  private String name;
  private String desc;
  private double cost;
  private long stock;
  private String companyUserName;
  private List<Review> reviews;

  public ConcreteProduct() {
  }

  @Override
  public double getCost() {
    return cost;
  }

  @Override
  public String getDesc() {
    return desc;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public long getStock() {
    return stock;
  }

  @Override
  public void setCost(double cost) {
    this.cost = cost;
  }

  @Override
  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setStock(long stock) {
    this.stock = stock;
  }

  @Override
  public void setCompanyUserName(String companyUserName) {
    this.companyUserName = companyUserName;
  }

  @Override
  public String getCompanyUserName() {
    return companyUserName;
  }

  @Override
  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public List<Review> getReviews() {
    return reviews;
  }
}
