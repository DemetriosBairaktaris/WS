package edu.luc.comp433.service.facade;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

//TODO add partner update methods

/**
 * This class sets up the facade between the domain layer and the service layer.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface DomainFacade {

  public List<String> searchProduct(String productName) throws SQLException;

  public boolean checkAvailability(String productName) throws SQLException;

  public boolean buyProduct(String customerName, String productName, long quantity, int orderId) throws SQLException;

  public boolean fulfillOrder(int orderId);

  public boolean cancelOrder(int orderId);

  public boolean refund(int orderId);

  public boolean shipOrder(int orderId);

  public String getOrderStatus(int orderId);

  public boolean addCustomer(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException;
  
  public boolean checkCustomerStatus(String userName) throws SQLException;
  
  public boolean deleteCustomer(String userName) throws SQLException;
  
  public boolean updateCustomerName(String userName, String firstName, String lastName) throws SQLException;
  
  public boolean updateCustomerAddress(String userName, String address) throws SQLException;
  
  public boolean updateCustomerPhone(String userName, String phone) throws SQLException;
  
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException;
  
  public boolean addReview(String userName, String productName, String review, int rating);
  
  public boolean addPartner(String userName, String companyName, String address, String phone) throws SQLException, Exception;
  
  public boolean deletePartner(String userName) throws SQLException, Exception;
  
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc, double cost, long stock) throws SQLException, Exception;
  
  public String getPartnerSales(String userName);
  
  public String generateReport();
  
  public boolean settleAccount(String userName);
}
