package edu.luc.comp433.domain.order;

import java.util.Date;
import java.util.List;

public interface Order {
  public void addOrderDetail(OrderDetail detail);

  public List<OrderDetail> getDetails();

  public double getId();

  public String getStatus();

  public Date getTimestamp();

  public void removeOrderDetail(OrderDetail detail);

  public void setDetails(List<OrderDetail> details);

  public void setId(double id);

  public void setStatus(String status);

  public void setTimestamp();
}
