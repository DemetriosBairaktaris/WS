package edu.luc.comp433.domain.order;

import edu.luc.comp433.domain.product.Product;

public class ConcreteOrderDetail implements OrderDetail {

  private double id;
  private Product product;
  private long quantity;
  private String status;

  public ConcreteOrderDetail() {
  }

  @Override
  public double getId() {
    return id;
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
  public void setId(double id) {
    this.id = id;
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
}
