package edu.luc.comp433.service.facade;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.product.ProductManager;

/**
 * This class sets up the facade between the domain layer and the service layer.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface DomainFacade {

  public void setCustomers(CustomerManager customers);
  
  public CustomerManager getCustomers();
  
  public void setPartners(PartnerManager partners);
  
  public PartnerManager getPartners();
  
  public void setOrders(OrderManager orders);
  
  public OrderManager getOrders();
  
  public void setProducts(ProductManager products);
  
  public ProductManager getProducts();
  
  public List<String> searchProduct(String productName) throws SQLException, Exception;

  public boolean checkAvailability(String productName) throws SQLException, Exception;

  public int buyProduct(String customerName, String productName, long quantity, int orderId)
      throws SQLException, Exception;

  public boolean fulfillOrder(int orderId) throws SQLException, Exception;

  public int cancelOrder(int orderId) throws SQLException, Exception;

  public boolean shipOrder(int orderId) throws SQLException, Exception;

  public String getOrderStatus(int orderId) throws SQLException, Exception;

  public boolean addCustomer(String userName, String firstName, String lastName, String address,
      String phone, String cardName, String cardNumber, String cvv, String expiration)
      throws SQLException, ParseException;

  public boolean checkCustomerStatus(String userName) throws SQLException;

  public boolean deleteCustomer(String userName) throws SQLException;

  public boolean updateCustomerName(String userName, String firstName, String lastName)
      throws SQLException;

  public boolean updateCustomerAddress(String userName, String address) throws SQLException;

  public boolean updateCustomerPhone(String userName, String phone) throws SQLException;

  public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv,
      String expiration) throws SQLException, ParseException;

  public boolean addReview(String userName, String productName, String review, int rating)
      throws SQLException, Exception;

  public boolean addPartner(String userName, String companyName, String address, String phone)
      throws SQLException, Exception;

  public boolean deletePartner(String userName) throws SQLException, Exception;

  public boolean acceptPartnerProduct(String userName, String productName, String productDesc,
      double cost, long stock) throws SQLException, Exception;

  public boolean updatePartnerName(String userName, String companyName)
      throws SQLException, Exception;

  public boolean updatePartnerAddress(String userName, String address)
      throws SQLException, Exception;

  public boolean updatePartnerPhone(String userName, String phone) throws SQLException, Exception;

  public String getPartnerSales(String userName) throws Exception;
}
