package edu.luc.comp433.domain.order;

public class OrderDetail {

    private double id;
    private double orderID;
    private double itemID;
    private long quantity;
    private String status;
    
    public OrderDetail() {}
    
    public void setID(double id) {
	this.id = id;
    }

    public double getId() {
        return id;
    }

    public double getOrderID() {
        return orderID;
    }

    public void setOrderID(double orderID) {
        this.orderID = orderID;
    }

    public double getItemID() {
        return itemID;
    }

    public void setItemID(double itemID) {
        this.itemID = itemID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
