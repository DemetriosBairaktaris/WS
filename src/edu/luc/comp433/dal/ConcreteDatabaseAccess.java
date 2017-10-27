package edu.luc.comp433.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.customer.Payment;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;
import edu.luc.comp433.domain.product.Review;

public class ConcreteDatabaseAccess implements DatabaseAccess {
  // private final String JDBC_DRIVER = "";
  private String DB_URL = "jdbc:postgresql:COMP433";
  // Database credentials
  private String USER = "postgres";
  private String PASS = "root";
  private Connection db;
  private Statement stmt;
  private ApplicationContext context;

  public ConcreteDatabaseAccess() throws SQLException {
    DB_URL = "jdbc:postgresql://ec2-54-163-233-201.compute-1.amazonaws.com:5432/dej2ecm8hpoisr"
        + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    USER = "evtgoojkjfryzn";
    PASS = "a8c878c4bf9212dcbfe7b1de5f7ff345be7be1a7d5e14bb7407a739ed4223d08";
    db = DriverManager.getConnection(DB_URL, USER, PASS);
    stmt = db.createStatement();
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");

  }

  @Override
  public void closeConnections() {
    try {
      stmt.close();
      db.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void finalize() {
    this.closeConnections();
  }

  @Override
  public int insertOrder(Order order) throws SQLException {
    if (order.getOrderId() > 0) {
      System.out.println("don't update an order through here, call updateOrder().... ");
      // This method does not insert the orderDetails!!!! pass those in through
      // updateOrder()
      return -1;

    }
    String sql = "INSERT INTO ORDERS (USER_NAME) VALUES ('%s') ;";
    sql = String.format(sql, order.getCustomer());

    PreparedStatement statementWithKey = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    int success = statementWithKey.executeUpdate();
    if (success == 0) {
      return -1;
    }

    ResultSet rs = statementWithKey.getGeneratedKeys();
    if (rs.next()) {
      return rs.getInt(1);
    } else {
      return -1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Order> getOrdersFromPartner(String userName) throws Exception {

    String sql = "SELECT ORDERS.ORDER_ID FROM ORDERS, ORDER_DETAILS, PARTNERS, PRODUCTS WHERE PARTNERS.PARTNER_USER_NAME = '%s' "
        + " AND ORDER_DETAILS.ORDER_ID = ORDERS.ORDER_ID AND ORDER_DETAILS.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND PRODUCTS.PARTNER_USER_NAME = PARTNERS.PARTNER_USER_NAME ;";
    sql = String.format(sql, userName);
    Statement newStatement = db.createStatement();
    ResultSet rs = newStatement.executeQuery(sql);
    List<Order> orders = (List<Order>) context.getBean("linkedList");
    while (rs.next()) {
      orders.add(this.getOrder((double) rs.getInt(1)));
    }
    return orders;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Order getOrder(double orderId) throws Exception {
    int oId = (int) orderId;
    String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = %d";
    sql = String.format(sql, oId);
    Order o = (Order) context.getBean("order");
    Statement newStatement = db.createStatement();
    ResultSet rs = newStatement.executeQuery(sql);
    if (rs.next()) {
      o.setOrderId(rs.getInt(1));
      o.setCustomer(rs.getString(2));
      o.setTimestamp(Date.valueOf(rs.getDate(3).toLocalDate()));
      o.setStatus(rs.getString(4));
    } else {
      return null;
    }
    sql = "SELECT * FROM ORDER_DETAILS WHERE ORDER_ID = %d";
    sql = String.format(sql, oId);
    List<OrderDetail> orderDetails = (List<OrderDetail>) context.getBean("linkedList");

    rs = newStatement.executeQuery(sql);
    while (rs.next()) {
      OrderDetail od = (OrderDetail) context.getBean("orderDetail");
      Product p = (Product) context.getBean("product");
      int productId = rs.getInt(4);
      od.setQuantity(rs.getInt(5));
      od.setStatus(rs.getString(6));
      String[] owner_product = this.getProductOwnerAndProductName(productId);
      String partnerUserName = owner_product[0];
      String productName = owner_product[1];
      p = this.getProductFromPartner(productName, this.getPartnerProfile(partnerUserName));
      od.setProduct(p);
      od.setCompany(partnerUserName);
      orderDetails.add(od);

    }
    o.setDetails(orderDetails);
    return o;
  }

  @Override
  public boolean updateOrder(Order order) throws SQLException {

    // It looks as if the only logical thing to change in an order is the status
    // all others (order_id, customers_user_name, order_date) are immutable.
    /**
     * Steps 1. update (SQL) the order by updating all but primary key 2. delete all
     * orderdetails with ^ pkey 3. insert all order details as new ones
     */
    String sql = "UPDATE ORDERS SET ORDER_STATUS = '%s' WHERE ORDER_ID = %d ; ";
    sql = String.format(sql, order.getStatus(), (int) order.getOrderId());
    if (stmt.executeUpdate(sql) == 0) {
      return false;
    }

    String deleteOrderDetailsSql = "DELETE FROM ORDER_DETAILS WHERE ORDER_ID = %d";
    deleteOrderDetailsSql = String.format(deleteOrderDetailsSql, order.getOrderId());

    stmt.executeUpdate(deleteOrderDetailsSql);

    int orderId = order.getOrderId();
    String userName = order.getCustomer();
    int productId;

    for (OrderDetail od : order.getDetails()) {
      productId = this.getProductId(od.getProduct().getName(), od.getProduct().getCompanyUserName());

      String reinsertOrderDetailsSql = "INSERT INTO ORDER_DETAILS (ORDER_ID,USER_NAME,PRODUCT_ID,QUANTITY, STATUS) VALUES ("
          + "%d,'%s',%d,%d,'%s');";
      reinsertOrderDetailsSql = String.format(reinsertOrderDetailsSql, orderId, userName, productId, od.getQuantity(),
          od.getStatus());
      stmt.executeUpdate(reinsertOrderDetailsSql);
    }
    return true;
  }

  @Override
  public boolean deleteOrder(Order order) throws SQLException {
    if (order.getOrderId() < 1) {
      System.out.println("Error:  could not delete the order because your order id is invalid");
      System.out.println("Did you forget to setOrderId?");
      return false;
    }

    String sql = "DELETE FROM ORDERS WHERE ORDER_ID = %d ; ";
    sql = String.format(sql, order.getOrderId());
    if (stmt.executeUpdate(sql) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean insertPartner(PartnerProfile profile) throws Exception, SQLException { // good

    String sql = "INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ( "
        + this.wrapSingleQuotes(profile.getUserName()) + "," + this.wrapSingleQuotes(profile.getName()) + ","
        + this.wrapSingleQuotes(profile.getAddress()) + "," + this.wrapSingleQuotes(profile.getPhone()) + " );";

    int success = stmt.executeUpdate(sql);
    if (success == 0) {
      System.out.println("Couln't insert row into Partners");
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean updatePartner(PartnerProfile profile) throws Exception, SQLException { // good
    if (this.deletePartner(profile.getUserName())) {
      return this.insertPartner(profile);
    } else {
      return false;
    }
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException { // good

    String partnerName = userName;
    String sql = "DELETE FROM PARTNERS WHERE PARTNER_USER_NAME = " + this.wrapSingleQuotes(partnerName) + " ; ";
    int success = stmt.executeUpdate(sql);
    if (success == 0) {
      System.out.println("Unable to delete partner");
      return false;
    }
    return true;
  }

  @Override
  public PartnerProfile getPartnerProfile(String userName) throws Exception, SQLException { // good
    String sql = "SELECT * FROM PARTNERS WHERE PARTNER_USER_NAME = " + this.wrapSingleQuotes(userName) + " ; ";
    ResultSet rs = stmt.executeQuery(sql);
    if (!rs.next()) {
      throw new Exception("Partner does not exist");
    } else {
      PartnerProfile p = (PartnerProfile) context.getBean("partner");
      p.setUserName(rs.getString(1));
      p.setName(rs.getString(2));
      p.setAddress(rs.getString(3));
      p.setPhone(rs.getString(4));
      return p;
    }
  }

  @Override
  public boolean insertProduct(Product product) throws SQLException { // good
    String sql = "INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION," + "COST,STOCK,PARTNER_USER_NAME) VALUES ("
        + this.wrapSingleQuotes(product.getName()) + "," + this.wrapSingleQuotes(product.getDesc()) + ","
        + product.getCost() + ", " + product.getStock() + ", " + this.wrapSingleQuotes(product.getCompanyUserName())
        + ") ; ";

    PreparedStatement statementWithKeys = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    int success;
    try {
      success = statementWithKeys.executeUpdate();
    } catch (PSQLException e) {
      System.out.println(e.getMessage());
      success = 0;
    }
    if (success == 0) {
      return false;
    }
    
    updateProductReviews(product) ; 
//    /*
//     * Continue on to insert reviews
//     */
//    String reviewSql;
//    int productId;
//    ResultSet keys = statementWithKeys.getGeneratedKeys();
//    if (keys.next()) {
//      productId = keys.getInt(1);
//      reviewSql = "Insert into reviews (Product_id,review_rating,review_content) values (" + "%d, %d, '%s') ; ";
//    } else {
//      return false;
//    }
//
//    for (Review r : product.getReviews()) {
//      reviewSql = String.format(reviewSql, productId, r.getRating(), r.getReview());
//      success = stmt.executeUpdate(reviewSql);
//      if (success == 0) {
//        return false;
//      }
//    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getAllProductsFromPartner(String companyUserName) throws Exception {

    String sql = "select * from products where products.partner_user_name = '%s' order by products.product_name asc;";

    sql = String.format(sql, companyUserName);
    List<Product> products = (List<Product>) context.getBean("linkedList");
    Statement newStatement = db.createStatement();

    ResultSet rs = newStatement.executeQuery(sql);
    Product p;

    while (rs.next()) {
      p = this.getProductFromPartner(rs.getString(2), this.getPartnerProfile(rs.getString(6)));
      products.add(p);
    }

    return products;

  }

  @Override
  public boolean updateProduct(Product product) throws SQLException {
    boolean ableToUpdate = true;
    String name = product.getName();
    String companyUserName = product.getCompanyUserName();
    long stock = product.getStock();
    double cost = product.getCost();
    String desc = product.getDesc();

    db.setAutoCommit(false);

    String sql = "UPDATE PRODUCTS SET STOCK = %d, COST = %f, DESCRIPTION = '%s' "
        + "WHERE PRODUCT_NAME = '%s' and PARTNER_USER_NAME = '%s' ; ";
    System.out.println(sql);
    sql = String.format(sql, stock, cost, desc, name, companyUserName);
    Statement newStatement = db.createStatement();
    int numberOfRowsChanged = newStatement.executeUpdate(sql);
    ableToUpdate = (numberOfRowsChanged == 0) ? false : true;
    updateProductReviews(product);
    db.commit();
    db.setAutoCommit(true);
    newStatement.close();
    return ableToUpdate;
  }

  private void updateProductReviews(Product product) throws SQLException {
    String productName = product.getName();
    String companyUserName = product.getCompanyUserName();
    String productIdSql = "select product_id from products where product_name = '%s' and partner_user_name = '%s' ; ";
    productIdSql = String.format(productIdSql, productName, companyUserName);
    Statement newStatement = db.createStatement();
    ResultSet productResults = newStatement.executeQuery(productIdSql);
    productResults.next();
    int columnNumber = 1;
    int productId = productResults.getInt(columnNumber);
    String deleteReviewsSql = "delete from reviews where product_id = %d; ";
    deleteReviewsSql = String.format(deleteReviewsSql, productId);
    newStatement.executeUpdate(deleteReviewsSql);
    List<Review> reviews = product.getReviews();

    newStatement.clearBatch();
    for (Review r : reviews) {
      String sql = "insert into reviews (product_id,review_rating,review_content) values (%d,%d,'%s') ; ";
      sql = String.format(sql, productId, r.getRating(), r.getReview());
      newStatement.addBatch(sql);
    }

    int[] results = newStatement.executeBatch();
    for (int i = 0; i < results.length; i++) {
      if (results[i] < 0) {
        throw new SQLException();
      }
    }

  }

  @SuppressWarnings("unchecked")
  @Override
  public Product getProductFromPartner(String productName, PartnerProfile profile) throws SQLException {
    String sql;
    Product p = null;
    sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = " + this.wrapSingleQuotes(productName)
        + " AND PARTNER_USER_NAME = " + this.wrapSingleQuotes(profile.getUserName()) + " ;";
    Statement newStatment = db.createStatement();
    ResultSet rs = newStatment.executeQuery(sql);
    int productId;
    if (rs.next()) {
      p = (Product) context.getBean("product");
      productId = rs.getInt(1);
      p.setName(rs.getString(2));
      p.setDesc(rs.getString(3));
      p.setCost(rs.getDouble(4));
      p.setStock(rs.getInt(5));
      p.setCompanyUserName(rs.getString(6));

    } else {
      newStatment.close();
      return null;
    }

    String get_review_sql = "Select * from reviews where product_id = %d";
    get_review_sql = String.format(get_review_sql, productId);
    rs = newStatment.executeQuery(get_review_sql);
    List<Review> reviews = (List<Review>) context.getBean("linkedList");
    while (rs.next()) {
      Review r = (Review) context.getBean("review");
      r.setRating(rs.getInt(3));
      r.setReview(rs.getString(4));
      reviews.add(r);
    }
    p.setReviews(reviews);
    newStatment.close();
    return p;

  }

  private String[] getProductOwnerAndProductName(int id) throws SQLException {
    String sql = "Select partners.partner_user_name, products.product_id,products.product_name from partners, products where"
        + " partners.partner_user_name = products.partner_user_name and products.product_id = %d";
    sql = String.format(sql, id);
    Statement newStatement = db.createStatement();
    ResultSet rs = newStatement.executeQuery(sql);
    if (rs.next()) {
      if (rs.getInt(2) == id) {
        String[] owner_product = { rs.getString(1), rs.getString(3) };
        return owner_product;
      } else {
        return null;
      }
    } else {
      return null;
    }

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getProduct(String productName) throws Exception {// good{
    String sql;
    Product p = null;
    sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = " + this.wrapSingleQuotes(productName) + " ;";
    Statement newStatement = db.createStatement();
    ResultSet rs = newStatement.executeQuery(sql);
    List<Product> products = (List<Product>) context.getBean("linkedList");
    while (rs.next()) {
      p = (Product) context.getBean("product");
      p.setName(rs.getString(2));
      p.setDesc(rs.getString(3));
      p.setCost(rs.getDouble(4));
      p.setStock(rs.getInt(5));
      p.setCompanyUserName(rs.getString(6));
      p = this.getProductFromPartner(p.getName(), this.getPartnerProfile(p.getCompanyUserName()));
      products.add(p);
    }
    return products;
  }

  @Override
  public boolean deleteProduct(Product product) throws SQLException { // good{
    String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_NAME = '%s' and 	PARTNER_USER_NAME = '%s'";
    sql = String.format(sql, product.getName(), product.getCompanyUserName());
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
    String sql = "INSERT INTO CUSTOMERS (USER_NAME,CUSTOMER_FIRST_NAME,CUSTOMER_LAST_NAME,"
        + "CUSTOMER_ADDRESS, CUSTOMER_PHONE)" + " VALUES ( " + this.wrapSingleQuotes(customer.getUserName()) + ", "
        + this.wrapSingleQuotes(customer.getFirstName()) + ", " + this.wrapSingleQuotes(customer.getLastName()) + ","
        + this.wrapSingleQuotes(customer.getAddress()) + "," + this.wrapSingleQuotes(customer.getPhone()) + ") ; ";
    if (stmt.executeUpdate(sql) == 0) {
      db.rollback();
      db.setAutoCommit(true);
      return false;
    }

    Payment p = customer.getPayment();
    sql = "INSERT INTO CUSTOMER_PAYMENTS (USER_NAME,CARD_NAME,CARD_NUMBER,CVV,EXPIRATION) VALUES" + "("
        + this.wrapSingleQuotes(customer.getUserName()) + "," + this.wrapSingleQuotes(p.getCardName()) + " ,"
        + this.wrapSingleQuotes(p.getCardNumber()) + " , " + this.wrapSingleQuotes(p.getCvv()) + ", "
        + this.wrapSingleQuotes(p.getExpiration().toString()) + ") ; ";

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
    if (this.deleteCustomer(customer.getUserName())) {
      return this.insertCustomer(customer);
    } else {
      return false;
    }
  }

  @Override
  public boolean deleteCustomer(String username) throws SQLException {
    String sql = "DELETE FROM CUSTOMERS WHERE USER_NAME = " + this.wrapSingleQuotes(username) + " ;";
    if (stmt.executeUpdate(sql) > 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean deleteCustomer(Customer customer) throws SQLException {
    String sql = "DELETE FROM CUSTOMERS WHERE USER_NAME = " + this.wrapSingleQuotes(customer.getUserName()) + " ;";
    if (stmt.executeUpdate(sql) > 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Customer getCustomer(String userName) throws SQLException {
    Statement newStatement = db.createStatement();
    String getConsumerCredSql = "SELECT * FROM CUSTOMERS WHERE USER_NAME = " + this.wrapSingleQuotes(userName) + " ;";
    String getPaymentSql = "SELECT CUSTOMERS.USER_NAME,CUSTOMERS.CUSTOMER_FIRST_NAME,CUSTOMERS.CUSTOMER_LAST_NAME,"
        + "CUSTOMER_PAYMENTS.CARD_NAME,CUSTOMER_PAYMENTS.CARD_NUMBER, CUSTOMER_PAYMENTS.CVV, CUSTOMER_PAYMENTS.EXPIRATION FROM CUSTOMERS, CUSTOMER_PAYMENTS WHERE"
        + " CUSTOMERS.USER_NAME = CUSTOMER_PAYMENTS.USER_NAME AND CUSTOMERS.USER_NAME = "
        + this.wrapSingleQuotes(userName) + " ;";

    ResultSet rs = newStatement.executeQuery(getConsumerCredSql);
    Customer c = (Customer) context.getBean("customer");

    if (rs.next()) {
      c.setUserName(rs.getString(1));
      c.setFirstName(rs.getString(2));
      c.setLastName(rs.getString(3));
      c.setAddress(rs.getString(4));
      c.setPhone(rs.getString(5));
    } else {
      newStatement.close();
      return null;
    }

    Payment p = (Payment) context.getBean("payment");
    rs = newStatement.executeQuery(getPaymentSql);
    if (rs.next()) {
      // c.setUserName(rs.getString(1));
      // c.setFirstName(rs.getString(2));
      // c.setLastName(rs.getString(3));
      p = (Payment) context.getBean("payment");
      p.setCardName(rs.getString(4));
      p.setCardNumber(rs.getString(5));
      p.setCvv(rs.getString(6));
      p.setExpiration(Date.valueOf((rs.getDate(7).toLocalDate())));
      c.setPayment(p);
    } else {
      c.setPayment(null);
    }
    newStatement.close();
    return c;
  }

  private int getProductId(String name, String companyUserName) throws SQLException {
    String sql = "Select product_id from products where product_name = '%s' and partner_user_name = '%s' ;";
    sql = String.format(sql, name, companyUserName);
    Statement newStatement = db.createStatement();
    ResultSet rs = newStatement.executeQuery(sql);

    if (rs.next()) {
      int id = rs.getInt(1);
      newStatement.close();
      return id;

    }
    newStatement.close();
    return 0;

  }

}