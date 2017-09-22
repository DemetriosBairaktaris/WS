package edu.luc.comp433.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.customer.ConcreteCustomer;
import edu.luc.comp433.domain.customer.ConcretePayment;
import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.customer.Payment;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.ConcretePartnerProfile;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public class ConcreteDatabaseAccess implements DatabaseAccess {
  // private final String JDBC_DRIVER = "";
  private String DB_URL = "jdbc:postgresql:COMP433";
  // Database credentials
  private String USER = "postgres";
  private String PASS = "root";
  private Connection db;
  private Statement stmt;
  private ApplicationContext context = new ClassPathXmlApplicationContext(
      "/WEB-INF/app-context.xml");

  public ConcreteDatabaseAccess() throws SQLException {
    DB_URL = "jdbc:postgresql://ec2-54-163-233-201.compute-1.amazonaws.com:5432/dej2ecm8hpoisr"
        + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    USER = "evtgoojkjfryzn";
    PASS = "a8c878c4bf9212dcbfe7b1de5f7ff345be7be1a7d5e14bb7407a739ed4223d08";
    db = DriverManager.getConnection(DB_URL, USER, PASS);
    stmt = db.createStatement();
  }

  @Override
  public boolean insertOrder() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateOrderDetail(OrderDetail detail) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Order getOrder(double orderId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OrderDetail> getOrderDetails(Order order) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OrderDetail> getOrderDetails(double id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insertPartner(PartnerProfile profile) throws Exception, SQLException {

    String sql = "INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ( "
        + this.wrapSingleQuotes(profile.getUserName()) + ","
        + this.wrapSingleQuotes(profile.getName()) + ","
        + this.wrapSingleQuotes(profile.getAddress()) + ","
        + this.wrapSingleQuotes(profile.getPhone()) + " );";
    int success = stmt.executeUpdate(sql);
    if (success == 0) {
      System.out.println("Couln't insert row into Partners");
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean updatePartner(PartnerProfile profile) throws Exception, SQLException {
    if (this.deletePartner(profile.getUserName())) {
      return this.insertPartner(profile);
    } else {
      return false;
    }
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException {

    String partnerName = userName;
    String sql = "DELETE FROM PARTNERS WHERE PARTNER_USER_NAME = "
        + this.wrapSingleQuotes(partnerName) + " ; ";
    int success = stmt.executeUpdate(sql);
    if (success == 0) {
      System.out.println("Unable to delete partner");
      return false;
    }
    return true;
  }

  @Override
  public PartnerProfile getPartnerProfile(String userName) throws Exception, SQLException {
    String sql = "SELECT * FROM PARTNERS WHERE PARTNER_USER_NAME = "
        + this.wrapSingleQuotes(userName) + " ; ";

    ResultSet rs = stmt.executeQuery(sql);
    if (!rs.next()) {
      throw new Exception("Partner does not exist");
    } else {
      PartnerProfile p = new ConcretePartnerProfile();
      p.setUserName(rs.getString(1));
      p.setName(rs.getString(2));
      p.setAddress(rs.getString(3));
      p.setPhone(rs.getString(4));
      return p;
    }
  }

  @Override
  public boolean insertProduct(Product product) throws SQLException {
    String sql = "INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION," + "COST,STOCK,COMPANY) VALUES ("
        + this.wrapSingleQuotes(product.getName()) + "," + this.wrapSingleQuotes(product.getDesc())
        + "," + product.getCost() + ", " + product.getStock() + ", " + product.getCompanyName()
        + ") ; ";
    System.out.println(sql);
    int success;
    try {
      success = stmt.executeUpdate(sql);
    } catch (PSQLException e) {
      System.out.println(e.getStackTrace());
      success = 0;
    }
    if (success == 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean updateProduct(Product product) throws SQLException {
    if (this.deleteProduct(product.getName())) {
      return insertProduct(product);
    } else {
      return false;
    }
  }

  @Override
  public Product getProduct(String productName) throws SQLException {
    String sql;
    Product p = null;
    sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = " + this.wrapSingleQuotes(productName)
        + " ;";
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      p = (Product) context.getBean("product");
      p.setName(rs.getString(0));
      p.setDesc(rs.getString(1));
      p.setCost(rs.getDouble(2));
      p.setStock(rs.getInt(3));
      p.setCompanyName(rs.getString(4));
    }
    return p;
  }

  @Override
  public boolean deleteProduct(String productName) throws SQLException {
    // String.format("Hello %s, %d", "world", 42);
    String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_NAME = '%s'";
    System.out.println(sql);
    sql = String.format(sql, productName);
    if (stmt.executeUpdate(sql) == 0) {
      return false;
    } else {
      return true;
    }
  }

  private String wrapSingleQuotes(String s) {
    String wrappedResult = "'" + s + "'";
    return wrappedResult;
  }

  @Override
  public boolean insertCustomer(Customer customer) throws SQLException {
    db.setAutoCommit(false);
    /**
     * Steps insert into consumer insert into payment
     */
    String sql = "INSERT INTO CUSTOMERS (CUSTOMER_USER_NAME,CUSTOMER_FIRST_NAME,CUSTOMER_LAST_NAME,"
        + "CUSTOMER_ADDRESS, CUSTOMER_PHONE)" + " VALUES ( "
        + this.wrapSingleQuotes(customer.getUserName()) + ", "
        + this.wrapSingleQuotes(customer.getFirstName()) + ", "
        + this.wrapSingleQuotes(customer.getLastName()) + ","
        + this.wrapSingleQuotes(customer.getAddress()) + ","
        + this.wrapSingleQuotes(customer.getPhone()) + ") ; ";

    if (stmt.executeUpdate(sql) == 0) {
      db.rollback();
      db.setAutoCommit(true);
      return false;
    }
    ;

    Payment p = customer.getPayment();
    sql = "INSERT INTO CUSTOMER_PAYMENTS (CUSTOMER_USER_NAME,CARD_NAME,CARD_NUMBER,CVV,EXPIRATION) VALUES"
        + "(" + this.wrapSingleQuotes(customer.getUserName()) + ","
        + this.wrapSingleQuotes(p.getCardName()) + " ," + this.wrapSingleQuotes(p.getCardNumber())
        + " , " + this.wrapSingleQuotes(p.getCvv()) + ", " + this.wrapSingleQuotes(p.getExpiration().toString()) + ") ; ";

    if (stmt.executeUpdate(sql) == 0) {
      db.rollback();
      db.setAutoCommit(true);
      return false;
    }

    db.commit();
    db.setAutoCommit(true);
    return true;
  }

  @Override
  public boolean updateCustomer(Customer customer) throws SQLException {
    if (this.getCustomer(customer.getUserName()).getUserName().equals(customer.getUserName())) {
      db.setAutoCommit(false);
      this.deleteCustomer(customer.getUserName());
      db.commit();
      if (this.insertCustomer(customer)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean deleteCustomer(String userName) throws SQLException {
    String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_USER_NAME = "
        + this.wrapSingleQuotes(userName) + " ;";
    if (stmt.executeUpdate(sql) > 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Customer getCustomer(String userName) throws SQLException {

    String getConsumerCredSql = "Select * from customers where customer_user_name = "
        + this.wrapSingleQuotes(userName) + ";";

    String getPaymentSql = "SELECT CUSTOMERS.CUSTOMER_USER_NAME,CUSTOMERS.CUSTOMER_FIRST_NAME,CUSTOMERS.CUSTOMER_LAST_NAME,"
        + "CUSTOMER_PAYMENTS.CARD_NAME,CUSTOMER_PAYMENTS.CARD_NUMBER, CUSTOMER_PAYMENTS.CVV FROM CUSTOMERS, CUSTOMER_PAYMENTS WHERE"
        + " CUSTOMERS.CUSTOMER_USER_NAME = CUSTOMER_PAYMENTS.CUSTOMER_USER_NAME 	AND CUSTOMERS.CUSTOMER_USER_NAME = "
        + this.wrapSingleQuotes(userName) + " ;";

    ResultSet rs = stmt.executeQuery(getConsumerCredSql);
    Customer c = new ConcreteCustomer();

    if (rs.next()) {
      c.setUserName(rs.getString(1));
      c.setFirstName(rs.getString(2));
      c.setLastName(rs.getString(3));
      c.setAddress(rs.getString(4));
      c.setPhone(rs.getString(5));
    } else {
      return null;
    }

    Payment p = new ConcretePayment();
    rs = stmt.executeQuery(getPaymentSql);
    if (rs.next()) {
      // c.setUserName(rs.getString(1));
      // c.setFirstName(rs.getString(2));
      // c.setLastName(rs.getString(3));
      p = new ConcretePayment();
      p.setCardName(rs.getString(4));
      p.setCardNumber(rs.getString(5));
      p.setCvv(rs.getString(6));
      p.setExpiration(Date.valueOf((rs.getString(7))));
      c.setPayment(p);
    } else {
      c.setPayment(null);
    }

    return c;
  }

}