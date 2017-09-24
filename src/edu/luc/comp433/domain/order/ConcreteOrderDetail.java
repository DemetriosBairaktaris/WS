package edu.luc.comp433.domain.order;

import edu.luc.comp433.domain.product.Product;

public class ConcreteOrderDetail implements OrderDetail {

  private Product product;
  private long quantity;
  private String status;
  private String company;

  public ConcreteOrderDetail() {
  }

  @Override
  public Product getProduct() {
    return product;
  }

  @Override
  public long getQuantity() {
    return quantity;
  }

  @Override
  public String getStatus() {
    return status;
  }

  @Override
  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  @Override
  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String getCompany() {
    return company;
  }
}
