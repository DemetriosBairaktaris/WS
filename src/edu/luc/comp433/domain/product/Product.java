package edu.luc.comp433.domain.product;

public interface Product {
  public void setName(String name);
  public String getName();
  public void setDesc(String desc);
  public String getDesc();
  public void setCost(double cost);
  public double getCost();
  public void setStock(long stock);
  public long getStock();
}
