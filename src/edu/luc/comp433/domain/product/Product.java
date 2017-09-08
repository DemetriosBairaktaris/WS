package edu.luc.comp433.domain.product;

public class Product {

    private String name;
    private String desc;
    private double cost;
    private double id;
    private double company;
    private long stock;

    public Product() {
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }
    
    public void setCost(double cost) {
	this.cost = cost;
    }
    
    public String getName() {
	return name;
    }
    
    public String getDesc() {
	return desc;
    }
    
    public double getCost() {
	return cost;
    }
    
    public void setID(double id) {
	this.id = id;
    }
    
    public double getID() {
	return id;
    }
    
    public void setCompany(double company) {
	this.company = company;
    }
    
    public double getCompany() {
	return company;
    }
    
    public void setStock(long stock) {
	this.stock = stock;
    }
    
    public long getStock() {
	return stock;
    }
}
