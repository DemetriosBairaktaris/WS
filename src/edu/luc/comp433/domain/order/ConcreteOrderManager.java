package edu.luc.comp433.domain.order;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public class ConcreteOrderManager implements OrderManager {

  private ApplicationContext context = new ClassPathXmlApplicationContext(
      "/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }
  
  @Override
  public int createOrder(String customer) {
    Order order = (Order) context.getBean("order");
    order.setCustomer(customer);
    order.setStatus("open");
    return database.insertOrder(order);
  }

  @Override
  public int createOrderDetail(int orderId, Product product, long quantity) {
    Order order = database.getOrder(orderId);
    OrderDetail orderDetail = (OrderDetail) context.getBean("orderDetail");
    orderDetail.setCompany(product.getCompanyName());
    orderDetail.setProduct(product);
    orderDetail.setQuantity(quantity);
    orderDetail.setStatus("open");
    order.addOrderDetail(orderDetail);
    return database.updateOrder(order);
  }

  @Override
  public boolean fulfillOrder(int orderId) {
    Order order = database.getOrder(orderId);
    for (int i = 0; i < order.getDetails().size(); i++) {
      order.getDetails().get(i).setStatus("fulfilled");
    }
    order.setStatus("fulfilled");
    return database.updateOrder(order);
  }

  @Override
  public boolean shipOrder(int orderId) {
    Order order = database.getOrder(orderId);
    for (int i = 0; i < order.getDetails().size(); i++) {
      order.getDetails().get(i).setStatus("shipped");
    }
    order.setStatus("shipped");
    return database.updateOrder(order);
  }

  @Override
  public boolean cancelOrder(int orderId) {
    Order order = database.getOrder(orderId);
    return database.deleteOrder(order);
  }

  @Override
  public Order getOrder(int orderId) {
    return database.getOrder(orderId);
  }

  @Override
  public OrderDetail getOrderDetail(int orderId, String productName) {
    Order order = database.getOrder(orderId);
    for (int i = 0; i < order.getDetails().size(); i++) {
      if (order.getDetails().get(i).getProduct().getName().equals(productName)) {
        return order.getDetails().get(i);
      }
    }
    return null;
  }

}
