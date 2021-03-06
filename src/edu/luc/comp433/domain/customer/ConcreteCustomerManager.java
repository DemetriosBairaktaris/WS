package edu.luc.comp433.domain.customer;

import edu.luc.comp433.dal.DatabaseAccess;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcreteCustomerManager implements CustomerManager {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/app-context.xml");
  private DatabaseAccess database;

  public ConcreteCustomerManager() {
  }

  @Override
  public boolean createCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, Date expiration) throws SQLException {
    Customer customer = (Customer) context.getBean("customer");
    customer.setUserName(userName);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setAddress(address);
    customer.setPhone(phone);
    customer.setPassword(password);
    Payment payment = (Payment) context.getBean("payment");
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCvv(cvv);
    payment.setExpiration(expiration);
    customer.setPayment(payment);
    return database.insertCustomer(customer);
  }

  @Override
  public boolean updateCustomer(String userName, String firstName, String lastName, String address, String phone,
      String password, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException {
    Customer customer = (Customer) context.getBean("customer");
    customer.setUserName(userName);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setAddress(address);
    customer.setPhone(phone);
    customer.setPassword(password);

    Payment payment = (Payment) context.getBean("payment");
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCvv(cvv);

    SimpleDateFormat format = new SimpleDateFormat("MM-yy");
    Date date = format.parse(expiration);
    payment.setExpiration(date);
    customer.setPayment(payment);
    boolean result = database.updateCustomer(customer);

    return result;
  }

  @Override
  public boolean deleteCustomer(String userName) throws SQLException {
    return database.deleteCustomer(database.getCustomer(userName));
  }

  @Override
  public Customer getCustomer(String userName) throws SQLException {
    return database.getCustomer(userName);
  }

  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }

  @Override
  public boolean updateAddress(String userName, String address) throws SQLException {
    Customer customer = database.getCustomer(userName);
    customer.setAddress(address);
    return database.updateCustomer(customer);
  }

  @Override
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException {
    Customer customer = database.getCustomer(userName);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    return database.updateCustomer(customer);
  }

  @Override
  public boolean updatePayment(String userName, String cardName, String cardNumber, String cvv, Date expiration)
      throws SQLException {
    Customer customer = database.getCustomer(userName);
    Payment payment = customer.getPayment();
    payment.setCardName(cardName);
    payment.setCardNumber(cardNumber);
    payment.setCvv(cvv);
    payment.setExpiration(expiration);
    customer.setPayment(payment);
    return database.updateCustomer(customer);
  }

  @Override
  public boolean updatePhone(String userName, String phone) throws SQLException {
    Customer customer = database.getCustomer(userName);
    customer.setPhone(phone);
    return database.updateCustomer(customer);
  }
}
