package edu.luc.comp433.dal;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public interface DatabaseAccess {

  /**
   * Order Related Methods
   */
  public int insertOrder(Order order) throws SQLException; // don't know what params or return will be, yet
  
  public boolean updateOrder(Order order) throws SQLException ; 
  
  public Order getOrder(double orderId) throws SQLException, Exception;
  
  public boolean deleteOrder(Order order) throws SQLException ;

  /**
   * Partner Related Methods
   * 
   * @throws SQLException
   * @throws Exception
   */
  public boolean insertPartner(PartnerProfile profile) throws SQLException, Exception;

  public boolean updatePartner(PartnerProfile profile) throws SQLException, Exception;

  public boolean deletePartner(String userName) throws SQLException;

  public PartnerProfile getPartnerProfile(String userName) throws Exception, SQLException;

  /**
   * Product Related Methods
   * 
   * @throws SQLException
   */
  public boolean insertProduct(Product product) throws SQLException;

  public boolean updateProduct(Product product) throws SQLException;

  public Product getProductFromPartner(String productName, PartnerProfile profile) throws SQLException;
  
  public List<Product> getProduct(String productName) throws SQLException, Exception ; 
  
  boolean deleteProduct(Product product) throws SQLException;
  
  public List<Product> getAllProductsFromPartner(String companyUserName) throws SQLException, Exception;

  /**
   * Consumer Methods
   * 
   * @throws SQLException
   */
  public boolean insertCustomer(Customer customer) throws SQLException;

  public boolean updateCustomer(Customer customer) throws SQLException;

  public boolean deleteCustomer(Customer customer) throws SQLException;

  public Customer getCustomer(String userName) throws SQLException;

  boolean deleteCustomer(String username) throws SQLException;



}