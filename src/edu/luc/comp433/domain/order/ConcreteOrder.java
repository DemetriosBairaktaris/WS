package edu.luc.comp433.domain.order;

import java.util.Date;
import java.util.List;

public class ConcreteOrder implements Order {

    private double id;
    private double customerID;
    private Date timestamp;
    private List<OrderDetail> details;
    
    public ConcreteOrder() {}
    
    @Override
    public void setID(double id) {
	this.id = id;
    }
    
    @Override
    public double getID() {
	return id;
    }
    
    @Override
    public void setCustomerID(double customerID) {
	this.customerID = customerID;
    }
    
    @Override
    public double getCustomerID() {
	return customerID;
    }
    
    @Override
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }
    
    @Override
    public Date getTimestamp() {
	return timestamp;
    }

    @Override
    public void setDetails(List<OrderDetail> details) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public List<OrderDetail> getDetails() {
      // TODO Auto-generated method stub
      return null;
    }
}
