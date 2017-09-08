package edu.luc.comp433.domain.order;

import java.util.Date;

public interface Order {
    public void setID(double id);
    public double getID();
    public void setCustomerID(double customerID);
    public double getCustomerID();
    public void setTimestamp(Date timestamp);
    public Date getTimestamp();
}
