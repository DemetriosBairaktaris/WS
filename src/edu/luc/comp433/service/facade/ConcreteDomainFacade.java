package edu.luc.comp433.service.facade;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.product.ProductManager;

//TODO uses professor's flow diagram to fix this
//TODO move to service level package
public class ConcreteDomainFacade implements DomainFacade {

  private CustomerManager customers;
  private PartnerManager partners;
  private OrderManager orders;
  private ProductManager products;

  public ConcreteDomainFacade() {
  }

  @Override
  public String searchProduct(String productName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean checkAvailability(String productName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean buyProduct(String customerName, String productName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean acceptPayment(String customerName, String productName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean createOrder(String customerName, String productName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean fulfillOrder(double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean cancelOrder(double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean refund(double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean shipOrder(double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getOrderStatus(double orderId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addCustomer(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv, String expiration) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean checkCustomerStatus(String userName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean deleteCustomer(String userName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateCustomerName(String userName, String firstName, String lastName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateCustomerAddress(String userName, String address) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateCustomerPhone(String userName, String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv,
      String expiration) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addReview(String userName, String productName, String review, int rating) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPartner(String userName, String companyName, String address, String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean deletePartner(String userName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc,
      double cost, long stock) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getPartnerSales(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String generateReport() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean settleAccount(String userName) {
    // TODO Auto-generated method stub
    return false;
  }
  
  
}
