package edu.luc.comp433.domain.order;

public interface OrderDetail {
    public void setID(double id);
    public double getId();
    public double getOrderID();
    public void setOrderID(double orderID);
    public double getItemID();
    public void setItemID(double itemID);
    public long getQuantity();
    public void setQuantity(long quantity);
    public String getStatus();
    public void setStatus(String status);
}
