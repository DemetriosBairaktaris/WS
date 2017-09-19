package edu.luc.comp433.domain.customer;

import java.sql.SQLException;
import java.util.LinkedList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.product.Product;

public class ConcreteCustomerManager implements CustomerManager {

  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  public ConcreteCustomerManager() {}
  
  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }
  
  @Override
  public boolean create(String userName, String firstName, String lastName, String address, String phone, 
      String cardName, String cardNumber, String CVV) throws SQLException {
    Customer customer = (Customer) context.getBean("customer");
    customer.setUserName(userName);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setAddress(address);
    customer.setPhone(phone);
    Payment payment = (Payment) context.getBean("payment");
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCVV(CVV);
    customer.setPayment(payment);
    if((database.insertConsumer(customer))) {
        return true ; 
    }
    else {
      return false;
    }
  }
  
  @Override
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException {
    Customer customer = database.getConsumer(userName);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    if (database.updateConsumer(customer)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateAddress(String userName, String address) throws SQLException {
    Customer customer = database.getConsumer(userName);
    customer.setAddress(address);
    if (database.updateConsumer(customer)) {
      return true ; 
    }
    else {
      return false;
    }
  }
  

  @Override
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException {
    Customer customer = database.getConsumer(userName);
    Payment payment = customer.getPayment();
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCVV(CVV);
    customer.setPayment(payment);
    if((database.updateConsumer(customer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public boolean updatePhone(String userName, String phone) throws SQLException {
    Customer customer = database.getConsumer(userName);
    customer.setPhone(phone);
    if((database.updateConsumer(customer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public boolean delete(String userName) throws SQLException {
    Customer customer = database.getConsumer(userName);
    if((database.deleteConsumer(customer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public Customer getCustomer(String userName) throws SQLException {
    return database.getConsumer(userName);
  }

  @Override
  public Order getOrder(String userName, double orderID) throws SQLException {
    Customer customer = database.getConsumer(userName);
    for (int i = 0; i < customer.getOrders().size(); i++) {
      if (customer.getOrders().get(i).getID() == orderID) {
        return customer.getOrders().get(i);
      }
    }
    return null;
  }

  @Override
  public boolean createOrder(String userName) throws SQLException {
    Customer customer = database.getConsumer(userName);
    Order order = (Order) context.getBean("order");
    order.setDetails(new LinkedList<OrderDetail>());
    order.setStatus("open");
    order.setTimestamp();
    order.setID(0); //TODO get ID from database here
    customer.addOrder(order);
    if (database.updateConsumer(customer)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean addOrderDetail(String userName, double orderID, String productName, long quantity) throws SQLException {
    Customer customer = database.getConsumer(userName);
    Order order = this.getOrder(userName, orderID);
    OrderDetail detail = (OrderDetail) context.getBean("orderDetail");
    Product product = database.getProduct(productName); //TODO implement this method in DAL
    detail.setProduct(product);
    detail.setQuantity(quantity);
    detail.setStatus("open");
    detail.setID(0); //TODO set ID here.
    order.addOrderDetail(detail);
    if (database.updateConsumer(customer)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateOrderQuantity(String userName, double orderID, String productName,
      long quantity) throws SQLException {
    Customer customer = database.getConsumer(userName);
    Order order = this.getOrder(userName, orderID);
    for (int i = 0; i < order.getDetails().size(); i++) {
      if (order.getDetails().get(i).getProduct().getName().equals(productName)) {
        if (quantity == 0) {
          OrderDetail detail = order.getDetails().get(i);
          order.removeOrderDetail(detail);
          if (database.updateConsumer(customer)) {
            return true;
          } else {
            return false;
          }
        } else {
          order.getDetails().get(i).setQuantity(quantity);
          if (database.updateConsumer(customer)) {
            return true;
          } else {
            return false;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean cancelOrder(String userName, double orderID) throws SQLException {
    Customer customer = database.getConsumer(userName);
    Order order = this.getOrder(userName, orderID);
    customer.removeOrder(order);
    if (database.updateConsumer(customer)) {
      return true;
    } else {
      return false;
    }
  }
}
