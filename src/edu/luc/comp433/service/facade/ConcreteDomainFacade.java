package edu.luc.comp433.service.facade;

import java.sql.SQLException;
import java.util.Date;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.product.ProductManager;

public class ConcreteDomainFacade implements DomainFacade {

  private CustomerManager customers;
  private PartnerManager partners;
  private OrderManager orders;
  private ProductManager products;
  private Date currentTime = new Date(System.currentTimeMillis());

  public ConcreteDomainFacade() {
  }

  @Override
  public String searchProduct(String productName) throws SQLException {
    return products.getProduct(productName).toString();
  }

  @Override
  public boolean checkAvailability(String productName) throws SQLException {
    long stock = products.getProduct(productName).getStock();
    if (stock >= 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean buyProduct(String customerName, String productName, long quantity, int orderId)
      throws SQLException {
    if (this.checkAvailability(productName)) {
      long newStock = products.getProduct(productName).getStock() - quantity;
      products.updateStock(productName, newStock);
      return acceptPayment(customerName, productName, quantity, orderId);
    } else {
      return false;
    }
  }

  private boolean acceptPayment(String customerName, String productName, long quantity, int orderId)
      throws SQLException {
    if (customers.getCustomer(customerName).getPayment().getExpiration()
        .compareTo(currentTime) > 0) {
      this.createOrder(customerName, productName, quantity, orderId);
      return true;
    }
    return false;
  }

  private boolean createOrder(String customerName, String productName, long quantity, int orderId)
      throws SQLException {
    if (orderId > 0) {
      orderId = orders.createOrder(customerName);
      return orders.createOrderDetail(orderId, products.getProduct(productName), quantity);
    } else {
      return orders.createOrderDetail(orderId, products.getProduct(productName), quantity);
    }
  }

  @Override
  public boolean fulfillOrder(int orderId) {
    return orders.fulfillOrder(orderId);
  }

  @Override
  public boolean cancelOrder(int orderId) {
    return this.refund(orderId);
  }

  @Override
  public boolean refund(int orderId) {
    int totalRefund = 0;
    for (int i = 0; i < orders.getOrder(orderId).getDetails().size(); i++) {
      totalRefund += orders.getOrder(orderId).getDetails().get(i).getProduct().getCost();
    }
    //TODO return refund to customer here.
    return false;
  }

  @Override
  public boolean shipOrder(int orderId) {
    return orders.shipOrder(orderId);
  }

  @Override
  public String getOrderStatus(int orderId) {
    return orders.getOrder(orderId).getStatus();
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
