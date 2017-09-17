package edu.luc.comp433.domain.order;

import java.util.Date;
import java.util.List;

public class ConcreteOrder implements Order {

    private double id;
    private Date timestamp;
    private String status;
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
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }
    
    @Override
    public Date getTimestamp() {
	return timestamp;
    }
    
    @Override
    public void setStatus(String status) {
      this.status = status;
    }
    
    @Override
    public String getStatus() {
      return status;
    }

    @Override
    public void setDetails(List<OrderDetail> details) {
      this.details = details;
    }

    @Override
    public List<OrderDetail> getDetails() {
      return details;
    }

    @Override
    public void addOrderDetail(OrderDetail detail) {
      details.add(detail);
    }

    @Override
    public void removeOrderDetail(OrderDetail detail) {
      details.remove(detail);
    }
}
