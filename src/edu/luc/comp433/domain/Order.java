package edu.luc.comp433.domain;

public class Order {

    private double number;
    private Item item;
    private double quantity;
    private String status;
    private boolean shipped;
    
    public Order() {
	shipped = false;
    }
    
    public void setNumber(double number) {
	this.number = number;
    }
    
    public double getNumber() {
	return number;
    }
    
    public void setItem(Item item) {
	this.item = item;
    }
    
    public Item getItem() {
	return item;
    }
    
    public void setQuantity(double quantity) {
	this.quantity = quantity;
    }
    
    public double getQuantity() {
	return quantity;
    }
    
    public double getTotalCost() {
	return item.getCost() * quantity;
    }
    
    public void setStatus(String status) {
	this.status = status;
    }
    
    public String getStatus() {
	return status;
    }
    
    public void setShipped(boolean shipped) {
	this.shipped = shipped;
    }
    
    public boolean getShipped() {
	return shipped;
    }
}
