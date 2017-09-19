package edu.luc.comp433.domain.consumer;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;

public class ConcreteConsumerManager implements ConsumerManager {

  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  public ConcreteConsumerManager() {}
  
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
    Consumer consumer = (Consumer) context.getBean("consumer");
    consumer.setUserName(userName);
    consumer.setFirstName(firstName);
    consumer.setLastName(lastName);
    consumer.setAddress(address);
    consumer.setPhone(phone);
    Payment payment = (Payment) context.getBean("payment");
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCVV(CVV);
    consumer.setPayment(payment);
    if((database.insertConsumer(consumer))) {
        return true ; 
    }
    else {
      return false;
    }
  }
  
  @Override
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException {
    Consumer consumer = database.getConsumer(userName);
    consumer.setFirstName(firstName);
    consumer.setLastName(lastName);
    if (database.updateConsumer(consumer)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateAddress(String userName, String address) throws SQLException {
    Consumer consumer = database.getConsumer(userName);
    consumer.setAddress(address);
    if (database.updateConsumer(consumer)) {
      return true ; 
    }
    else {
      return false;
    }
  }
  

  @Override
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException {
    Consumer consumer = database.getConsumer(userName);
    Payment payment = consumer.getPayment();
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCVV(CVV);
    consumer.setPayment(payment);
    if((database.updateConsumer(consumer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public boolean updatePhone(String userName, String phone) throws SQLException {
    Consumer consumer = database.getConsumer(userName);
    consumer.setPhone(phone);
    if((database.updateConsumer(consumer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public boolean delete(String userName) throws SQLException {
    Consumer consumer = database.getConsumer(userName);
    if((database.deleteConsumer(consumer))) {
      return true ; 
    }
    else {
      return false;
    }
  }

  @Override
  public Consumer getConsumer(String userName) throws SQLException {
    return database.getConsumer(userName);
  }
}
