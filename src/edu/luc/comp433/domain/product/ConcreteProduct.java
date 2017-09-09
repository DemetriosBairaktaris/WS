package edu.luc.comp433.domain.product;

public class ConcreteProduct implements Product {

    private String name;
    private String desc;
    private double cost;
    private double id;
    private double company;
    private long stock;

    public ConcreteProduct() {}

    @Override
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public void setDesc(String desc) {
	this.desc = desc;
    }
    
    @Override
    public void setCost(double cost) {
	this.cost = cost;
    }
    
    @Override
    public String getName() {
	return name;
    }
    
    @Override
    public String getDesc() {
	return desc;
    }
    
    @Override
    public double getCost() {
	return cost;
    }
    
    @Override
    public void setID(double id) {
	this.id = id;
    }
    
    @Override
    public double getID() {
	return id;
    }
    
    @Override
    public void setCompany(double company) {
	this.company = company;
    }
    
    @Override
    public double getCompany() {
	return company;
    }
    
    @Override
    public void setStock(long stock) {
	this.stock = stock;
    }
    
    @Override
    public long getStock() {
	return stock;
    }
}
