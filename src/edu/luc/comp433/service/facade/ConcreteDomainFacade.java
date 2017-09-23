package edu.luc.comp433.service.facade;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

  // TODO make this return more info for the product
  @Override
  public List<String> searchProduct(String productName) throws SQLException {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < products.getProducts(productName).size(); i++) {
      list.add(products.getProducts(productName).get(i).getName());
    }
    return list;
  }

  @Override
  public boolean checkAvailability(String productName) throws SQLException {
    long stock = 0;
    for (int i = 0; i < products.getProducts(productName).size(); i++) {
      stock = products.getProducts(productName).get(i).getStock();
      if (stock >= 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean buyProduct(String customerName, String productName, long quantity, int orderId)
      throws SQLException {
    long newStock = 0;
    if (this.checkAvailability(productName)) {
      for (int i = 0; i < products.getProducts(productName).size(); i++) {
        if (products.getProducts(productName).get(i).getStock() > quantity) {
          newStock = products.getProducts(productName).get(i).getStock() - quantity;
          String companyName = products.getProducts(productName).get(i).getCompanyName();
          products.updateStock(companyName, productName, newStock);
          return this.acceptPayment(companyName, customerName, productName, quantity, orderId);
        }
      }
    }
    return false;
  }

  private boolean acceptPayment(String companyName, String customerName, String productName,
      long quantity, int orderId) throws SQLException {
    if (customers.getCustomer(customerName).getPayment().getExpiration()
        .compareTo(currentTime) > 0) {
      this.createOrder(companyName, customerName, productName, quantity, orderId);
      return true;
    }
    return false;
  }

  //TODO this needs to be fixed
  private boolean createOrder(String companyName, String customerName, String productName,
      long quantity, int orderId) throws SQLException {
    if (orderId > 0) {
      orderId = orders.createOrder(customerName);
      for (int i = 0; i < products.getProducts(productName).size(); i++) {
        if (products.getProducts(productName).get(i).getName().equals(productName)
            && products.getProducts(productName).get(i).getCompanyName().equals(companyName)) {
          return orders.createOrderDetail(orderId, products.getProducts(productName).get(i), quantity);
        }
      }
    }
    return false;
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
  public boolean updateCustomerName(String userName, String firstName, String lastName)
      throws SQLException {
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
  public boolean addPartner(String userName, String companyName, String address, String phone)
      throws SQLException, Exception {
    return partners.createPartner(userName, companyName, address, phone);
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException, Exception {
    return partners.deletePartner(userName);
  }

  @Override
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc,
      double cost, long stock) throws SQLException, Exception {
    return products.addProduct(productName, productDesc, cost, stock,
        partners.getPartnerProfile(userName).getName());
  }

  //TODO add partner update methods here
  
  @Override
  public String getPartnerSales(String userName) {
    // TODO needs to be able to iterate through all orders.
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
