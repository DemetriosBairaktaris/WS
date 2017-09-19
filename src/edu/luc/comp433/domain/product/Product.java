package edu.luc.comp433.domain.product;

public interface Product {
  public double getCost();

  public String getDesc();

  public String getName();

  public long getStock();

  public void setCost(double cost);

  public void setDesc(String desc);

  public void setName(String name);

  public void setStock(long stock);
}
