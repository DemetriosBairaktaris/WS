package edu.luc.comp433.service.facade;

/**
 * This class sets up the facade between the domain layer and the service layer.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface DomainFacade {

  public String searchProduct(String productName);

  public boolean checkAvailability(String productName);

  public boolean buyProduct(String customerName, String productName);

  public boolean acceptPayment(String customerName, String productName);

  public boolean createOrder(String customerName, String productName);

  public boolean fulfillOrder(double orderId);

  public boolean cancelOrder(double orderId);

  public boolean refund(double orderId);

  public boolean shipOrder(double orderId);

  public String getOrderStatus(double orderId);

  public boolean addCustomer(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv, String expiration);
  
  public boolean checkCustomerStatus(String userName);
  
  public boolean deleteCustomer(String userName);
  
  public boolean updateCustomerName(String userName, String firstName, String lastName);
  
  public boolean updateCustomerAddress(String userName, String address);
  
  public boolean updateCustomerPhone(String userName, String phone);
  
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv, String expiration);
  
  public boolean addReview(String userName, String productName, String review, int rating);
  
  public boolean addPartner(String userName, String companyName, String address, String phone);
  
  public boolean deletePartner(String userName);
  
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc, double cost, long stock);
  
  public String getPartnerSales(String userName);
  
  public String generateReport();
  
  public boolean settleAccount(String userName);
}
