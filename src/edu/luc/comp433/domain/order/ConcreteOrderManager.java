package edu.luc.comp433.domain.order;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public class ConcreteOrderManager implements OrderManager {
  
  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
    private DatabaseAccess database;
  
  public ConcreteOrderManager() {}

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }
  
  @Override
  public boolean createOrder(double id, Date timestamp) {
    Order order = (Order) context.getBean("order");
    order.setID(id);
    order.setTimestamp(timestamp);
    order.setStatus("open");
    if (database.insertOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean createOrderDetail(double id, Product product, long quantity, String status,
      double orderID) {
    OrderDetail orderDetail = (OrderDetail) context.getBean("orderDetail");
    orderDetail.setID(id);
    orderDetail.setProduct(product);
    orderDetail.setQuantity(quantity);
    orderDetail.setStatus(status);
    if (this.addOrderDetail(orderID, orderDetail)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public boolean addOrderDetail(double id, OrderDetail detail) {
    Order order = database.getOrder(id);
    order.addOrderDetail(detail);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateOrder(String status, double id) {
    Order order = database.getOrder(id);
    order.setStatus(status);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateOrderDetail(String status, double id, double orderID) {
    Order order = database.getOrder(orderID);
    OrderDetail orderDetails = order.getDetails().get((int) id);
    orderDetails.setStatus(status);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateOrderDetailQuantity(long quantity, double id, double orderID) {
    Order order = database.getOrder(orderID);
    OrderDetail orderDetails = order.getDetails().get((int) id);
    orderDetails.setQuantity(quantity);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean removeOrderDetail(double id, double orderID) {
    Order order = database.getOrder(orderID);
    OrderDetail orderDetails = order.getDetails().remove((int) id);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean closeOrder(double id) {
    Order order = database.getOrder(orderID);
    order.setStatus(status);
    if (database.updateOrder(order)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public Order getOrder(double id) {
    return database.getOrder(id);
  }
}
