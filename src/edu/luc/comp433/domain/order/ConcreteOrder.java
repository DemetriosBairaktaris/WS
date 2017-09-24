package edu.luc.comp433.domain.order;

import java.sql.Date;
import java.util.List;

public class ConcreteOrder implements Order {

  private int orderId;
  private Date timestamp;
  private String status;
  private String customer;
  private List<OrderDetail> details;

  public ConcreteOrder() {
  }

  @Override
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public int getOrderId() {
    return orderId;
  }

  @Override
  public void addOrderDetail(OrderDetail detail) {
    details.add(detail);
  }

  @Override
  public List<OrderDetail> getDetails() {
    return details;
  }

  @Override
  public String getStatus() {
    return status;
  }

  @Override
  public Date getTimestamp() {
    return timestamp;
  }

  @Override
  public void removeOrderDetail(OrderDetail detail) {
    details.remove(detail);
  }

  @Override
  public void setDetails(List<OrderDetail> details) {
    this.details = details;
  }

  @Override
  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public void setTimestamp(Date date) {

  }

  @Override
  public void setCustomer(String customer) {
    this.customer = customer;
  }

  @Override
  public String getCustomer() {
    return customer;
  }
}
