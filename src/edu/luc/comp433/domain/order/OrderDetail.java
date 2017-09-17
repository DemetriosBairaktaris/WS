package edu.luc.comp433.domain.order;

import edu.luc.comp433.domain.product.Product;

public interface OrderDetail {
    public void setID(double id);
    public double getId();
    public Product getProduct();
    public void setProduct(Product product);
    public long getQuantity();
    public void setQuantity(long quantity);
    public String getStatus();
    public void setStatus(String status);
}
