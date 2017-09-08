package edu.luc.comp433.domain.product;

public interface Product {
    public void setName(String name);
    public void setDesc(String desc);
    public void setCost(double cost);
    public String getName();
    public String getDesc();
    public double getCost();
    public void setID(double id);
    public double getID();
    public void setCompany(double company);
    public double getCompany();
    public void setStock(long stock);
    public long getStock();
}
