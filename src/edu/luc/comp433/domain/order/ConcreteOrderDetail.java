package edu.luc.comp433.domain.order;

public class ConcreteOrderDetail implements OrderDetail {

    private double id;
    private double orderID;
    private double itemID;
    private long quantity;
    private String status;
    
    public ConcreteOrderDetail() {}
    
    @Override
    public void setID(double id) {
	this.id = id;
    }

    @Override
    public double getId() {
        return id;
    }

    @Override
    public double getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(double orderID) {
        this.orderID = orderID;
    }

    @Override
    public double getItemID() {
        return itemID;
    }

    @Override
    public void setItemID(double itemID) {
        this.itemID = itemID;
    }

    @Override
    public long getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
