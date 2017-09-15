package edu.luc.comp433.domain.order;

import java.util.Date;
import java.util.List;

public interface Order {
    public void setID(double id);
    public double getID();
    public void setCustomerID(double customerID);
    public double getCustomerID();
    public void setTimestamp(Date timestamp);
    public Date getTimestamp();
    public void setDetails(List<OrderDetail> details);
    public List<OrderDetail> getDetails();
}
