package edu.luc.comp433.domain;

public class Item {

    private String name;
    private String desc;
    private double cost;
    private double id;

    public Item() {
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
}
