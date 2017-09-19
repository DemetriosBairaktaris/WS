package edu.luc.comp433.domain.order;

import edu.luc.comp433.domain.product.Product;

public interface OrderDetail {
  public double getId();

  public Product getProduct();

  public long getQuantity();

  public String getStatus();

  public void setId(double id);

  public void setProduct(Product product);

  public void setQuantity(long quantity);

  public void setStatus(String status);
}
