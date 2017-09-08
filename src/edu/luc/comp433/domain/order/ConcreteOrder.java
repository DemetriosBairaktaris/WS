package edu.luc.comp433.domain.order;

import java.util.Date;

public class ConcreteOrder implements Order {

    private double id;
    private double customerID;
    private Date timestamp;
    
    public ConcreteOrder() {}
    
    public void setID(double id) {
	this.id = id;
    }
    
    public double getID() {
	return id;
    }
    
    public void setCustomerID(double customerID) {
	this.customerID = customerID;
    }
    
    public double getCustomerID() {
	return customerID;
    }
    
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }
    
    public Date getTimestamp() {
	return timestamp;
    }
}
