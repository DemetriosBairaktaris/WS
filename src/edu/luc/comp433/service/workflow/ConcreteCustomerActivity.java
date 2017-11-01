package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.luc.comp433.domain.customer.CustomerManager;

public class ConcreteCustomerActivity implements CustomerActivity {

  private CustomerManager customers;

  public ConcreteCustomerActivity() {
  }

  @Override
  public CustomerManager getCustomers() {
    return customers;
  }

  @Override
  public void setCustomers(CustomerManager customers) {
    this.customers = customers;
  }

  @Override
  public boolean addCustomer(String userName, String firstName, String lastName, String address, String phone,
      String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException {
    SimpleDateFormat format = new SimpleDateFormat("MM-yy");
    Date date = format.parse(expiration);
    return customers.createCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv, date);
  }
  
  public boolean updateCustomer(String userName, String firstName, String lastName, String address, String phone,
      String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException {
      return customers.updateCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv, expiration);
  }

  @Override
  public boolean checkCustomerStatus(String userName) throws SQLException {
    if (customers.getCustomer(userName) != null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean deleteCustomer(String userName) throws SQLException {
    return customers.deleteCustomer(userName);
  }

  @Override
  public boolean updateCustomerName(String userName, String firstName, String lastName) throws SQLException {
    return customers.updateName(userName, firstName, lastName);
  }

  @Override
  public boolean updateCustomerAddress(String userName, String address) throws SQLException {
    return customers.updateAddress(userName, address);
  }

  @Override
  public boolean updateCustomerPhone(String userName, String phone) throws SQLException {
    return customers.updatePhone(userName, phone);
  }

  @Override
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException {
    DateFormat format = new SimpleDateFormat("MM-yy");
    Date date = format.parse(expiration);
    return customers.updatePayment(userName, cardName, cardNumber, cvv, date);
  }

}
