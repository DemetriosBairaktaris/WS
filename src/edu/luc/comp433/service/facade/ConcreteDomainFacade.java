package edu.luc.comp433.service.facade;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    // TODO return refund to customer here.
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
      String phone, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException {
    DateFormat format = new SimpleDateFormat();
    Date date = format.parse(expiration);
    customers.createCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber,
        cvv, date);
    return false;
  }

  @Override
  public boolean checkCustomerStatus(String userName) throws SQLException {
    if (!customers.getCustomer(userName).equals(null)) {
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
  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv,
      String expiration) throws SQLException, ParseException {
    DateFormat format = new SimpleDateFormat();
    Date date = format.parse(expiration);
    return customers.updatePayment(userName, cardName, cardNumber, cvv, date);
  }

  @Override
  public boolean addReview(String userName, String productName, String review, int rating) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPartner(String userName, String companyName, String address, String phone) throws SQLException, Exception {
    return partners.createPartner(userName, companyName, address, phone);
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException, Exception {
    return partners.deletePartner(userName);
  }

  @Override
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc,
      double cost, long stock) throws SQLException, Exception {
    return products.addProduct(productName, productDesc, cost, stock, partners.getPartnerProfile(userName).getName());
  }

  @Override
  public String getPartnerSales(String userName) {
    //TODO needs to be able to iterate through all orders.
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
