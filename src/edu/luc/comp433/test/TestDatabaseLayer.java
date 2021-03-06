package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.comp433.dal.ConcreteDatabaseAccess;
import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.customer.ConcreteCustomer;
import edu.luc.comp433.domain.customer.ConcretePayment;
import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.customer.Payment;
import edu.luc.comp433.domain.order.ConcreteOrder;
import edu.luc.comp433.domain.order.ConcreteOrderDetail;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.ConcretePartnerProfile;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.ConcreteProduct;
import edu.luc.comp433.domain.product.ConcreteReview;
import edu.luc.comp433.domain.product.Product;
import edu.luc.comp433.domain.product.Review;

public class TestDatabaseLayer {

  private DatabaseAccess dal;
  private String partnerName1;

  public TestDatabaseLayer() {

    try {
      dal = new ConcreteDatabaseAccess();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void finalize() {
    dal.closeConnections();
  }

  @Before
  public void setUp() throws SQLException {

    partnerName1 = "management@johnsonandsons.com";
  }

  @After
  public void tearDown() throws SQLException {
    partnerName1 = null;
  }

  @Test
  public void testInsertPartner() throws Exception, SQLException {
    PartnerProfile partner = new ConcretePartnerProfile();
    partner.setUserName(partnerName1);
    partner.setName("Johnson and sons");
    partner.setAddress("1232 Lollypop Way");
    partner.setPhone("219-292-1111");
    partner.setPassword("root");
    assertTrue(dal.insertPartner(partner));
    assertTrue(dal.deletePartner(partner.getUserName()));
  }

  @Test
  public void testGetPartnerByName() throws Exception, SQLException {
    String partnerUserName = "management@newbie.com";
    String partnerName = "Newbie Co";
    PartnerProfile partner = new ConcretePartnerProfile();
    partner.setName(partnerName);
    partner.setUserName(partnerUserName);
    partner.setAddress("900 Walsh st.");
    partner.setPhone("283-000-987");
    partner.setPassword("root");
    assertTrue(dal.insertPartner(partner));

    if (dal.getPartnerProfile(partnerUserName).getUserName().equals(partnerUserName)) {
      assertTrue((partner.getUserName().equals(partnerUserName)));
      dal.deletePartner(partner.getUserName());
    } else {
      assertTrue(false);
    }
  }

  @Test
  public void testUpdatePartner() throws Exception, SQLException {
    String partnerUserName = "management@newbie.com";
    String partnerName = "Newbie Co";
    String address = "900 Walsh st.";
    String phone = "219-222-2222";
    String newPhone = "219-333-3333";
    String password = "root";
    PartnerProfile partner = new ConcretePartnerProfile();

    partner.setName(partnerName);
    partner.setUserName(partnerUserName);
    partner.setAddress(address);
    partner.setPhone(phone);
    partner.setPassword(password);

    dal.insertPartner(partner);

    partner.setPhone(newPhone);
    assertTrue(dal.updatePartner(partner));
    assertTrue(dal.getPartnerProfile(partnerUserName).getPhone().equals(newPhone));
    assertTrue(dal.deletePartner(partner.getUserName()));

  }

  @Test
  public void testDeletePartner() throws Exception {
    String partnerUserName = "management@newbie.com";
    String partnerName = "Newbie Co";
    String address = "900 Walsh St.";
    String phone = "219-222-2222";
    String password = "root";
    PartnerProfile partner = new ConcretePartnerProfile();

    partner.setName(partnerName);
    partner.setUserName(partnerUserName);
    partner.setAddress(address);
    partner.setPhone(phone);
    partner.setPassword(password);
    assertTrue(dal.insertPartner(partner));
    assertTrue(dal.deletePartner(partner.getUserName()));

  }

  @Test
  public void testInsertProduct() throws Exception {
    String productName = "WaxOn-WaxOff";
    String desc = "Simple floor wax."; // todo fix for apostraphe
    String partnerUserName = "wonderbread@gmail.com";
    double cost = 500000.00;
    int stock = 30;
    Product product = new ConcreteProduct();
    product.setName(productName);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);

    Review r = new ConcreteReview();
    r.setRating(5);
    r.setReview("The best wax evaaaaarr");
    product.setReviews(Arrays.asList(r));

    PartnerProfile profile = new ConcretePartnerProfile();

    profile.setUserName(partnerUserName);
    product.setCompanyUserName(partnerUserName);
    assertFalse(dal.insertProduct(product)); // should fail/ partner doesn't exist...
    profile.setUserName("management@target.com"); // change to one that does exist
    product.setCompanyUserName(profile.getUserName());
    assertTrue(dal.insertProduct(product)); // should pass :)
    assertEquals(dal.getProductFromPartner(productName, dal.getPartnerProfile(profile.getUserName())).getReviews()
        .get(0).getReview(), r.getReview());
    assertTrue(dal.deleteProduct(product));

  }

  @Test
  public void testGetProductList() throws Exception {
    String[] partners = { "management@target.com", "management@jcpenny.com" };
    int i = 0;
    while (i < 2) {
      Product p = new ConcreteProduct();
      List<Review> r = new LinkedList<>();
      p.setReviews(r);
      p.setCompanyUserName(partners[i++]);
      p.setCost(3);
      p.setDesc(String.valueOf(3));
      p.setName(String.valueOf(3340304));
      p.setStock(3);
      assertTrue(dal.insertProduct(p));
    }

    for (Product p : dal.getProduct(String.valueOf(3340304))) {
      assertTrue(partners[0].equals(p.getCompanyUserName()) || partners[1].equals(p.getCompanyUserName()));
      assertEquals(String.valueOf(3340304), p.getName());
      assertEquals(Arrays.asList(), p.getReviews());
      assertEquals(3, p.getCost(), 2);
      assertEquals(3, p.getStock());
      assertEquals(String.valueOf(3), p.getDesc());
      dal.deleteProduct(p);
    }

  }

  @Test
  public void testDeleteProduct() throws SQLException {
    String productName = "WaxOn-WaxOff";
    String desc = "Everyones favorite wax";
    String partnerUserName = "management@target.com";
    double cost = 500000.00;
    int stock = 30;
    Product product = new ConcreteProduct();

    List<Review> r = new LinkedList<>();
    r.add(new ConcreteReview());
    product.setName(productName);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);
    product.setReviews(r);

    PartnerProfile profile = new ConcretePartnerProfile();
    profile.setUserName(partnerUserName);
    product.setCompanyUserName(profile.getUserName());

    assertTrue(dal.insertProduct(product)); // should pass :)
    assertTrue(dal.deleteProduct(product));

  }

  @Test
  public void testInsertConsumer() throws SQLException {
    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";
    String password = "root";

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("2011-2-4"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    c.setPassword(password);

    assertTrue(dal.insertCustomer(c));
    assertTrue(dal.deleteCustomer(c));
  }

  @Test
  public void testGetConsumer() throws SQLException {
    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("2018-02-02"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    assertNotNull(dal);
    assertTrue(dal.insertCustomer(c));
    Customer gotten = dal.getCustomer(username);
    assertNotNull(gotten);
    assertTrue(c.getUserName().equals(gotten.getUserName()));
    assertEquals(payment.getCardName(), gotten.getPayment().getCardName());
    assertEquals(payment.getCardNumber(), gotten.getPayment().getCardNumber());
    assertEquals(payment.getCvv(), gotten.getPayment().getCvv());
    assertEquals(payment.getExpiration(), gotten.getPayment().getExpiration());
    assertTrue(dal.deleteCustomer(c.getUserName()));
  }

  @Test
  public void testUpdateConsumer() throws SQLException {
    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";
    String password = "root";

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("1980-2-2"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    c.setPassword(password);
    dal.deleteCustomer(c);
    assertTrue(dal.insertCustomer(c));
    String newFirstName = "Tee";
    c.setFirstName(newFirstName);
    assertTrue(dal.updateCustomer(c));

    assertTrue(newFirstName.equals(dal.getCustomer(username).getFirstName()));
    dal.deleteCustomer(c);
  }

  @Test
  public void testDeleteConsumer() throws SQLException {
    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("2019-2-2"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);

    assertTrue(dal.insertCustomer(c));
    assertTrue(dal.deleteCustomer(c));
  }

  @Test
  public void testCreateOrderDelete() throws SQLException {
    Order order = new ConcreteOrder();
    order.setDetails(new LinkedList<OrderDetail>());
    order.setStatus("open");

    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";

    order.setCustomer(username);

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("1980-2-2"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    try {
      assertTrue(dal.insertCustomer(c));
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      dal.deleteCustomer(c.getUserName());
    }
    order.setOrderId(dal.insertOrder(order));
    assertTrue(order.getOrderId() > 0);
    assertTrue(dal.deleteOrder(order));
    assertTrue(dal.deleteCustomer(c));
  }

  @Test
  public void testGetOrder() throws Exception {
    Order order = new ConcreteOrder();
    order.setDetails(new LinkedList<OrderDetail>());
    order.setStatus("open");

    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";

    order.setCustomer(username);

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("1980-2-2"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    try {
      assertTrue(dal.insertCustomer(c));
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      assertTrue(false);
    }
    order.setOrderId(dal.insertOrder(order));

    assertEquals(order.getOrderId(), dal.getOrder(order.getOrderId()).getOrderId());
    assertEquals(order.getCustomer(), dal.getOrder(order.getOrderId()).getCustomer());
  }

  @Test
  public void testUpdateOrder() throws Exception {
    Order order = new ConcreteOrder();
    order.setDetails(new LinkedList<OrderDetail>());
    order.setStatus("open");

    String username = "dougf@gmail.com";
    String firstName = "Doug";
    String lastName = "Frank";
    String address = "232 dafney st";
    String phone = "219-202-2222";

    order.setCustomer(username);

    Payment payment = new ConcretePayment();
    payment.setCardName("visa");
    payment.setCardNumber("2233333333334444");
    payment.setCvv("822");
    payment.setExpiration(Date.valueOf("1980-2-2"));

    Customer c = new ConcreteCustomer();
    c.setUserName(username);
    c.setFirstName(firstName);
    c.setLastName(lastName);
    c.setAddress(address);
    c.setPhone(phone);
    c.setPayment(payment);
    dal.deleteCustomer(c);
    try {
      assertTrue(dal.insertCustomer(c));
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      assertTrue(false);
    }

    order.setOrderId(dal.insertOrder(order));
    order = dal.getOrder(order.getOrderId());
    OrderDetail d = new ConcreteOrderDetail();
    int quantity = 500;

    String productName = "WaxOn-WaxOff";
    String desc = "Everyones favorite wax"; // todo fix for apostraphe
    String partnerUserName = "management@target.com";
    double cost = 500000.00;
    int stock = 30;
    Product product = new ConcreteProduct();
    product.setName(productName);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);
    product.setCompanyUserName(partnerUserName);

    Review r = new ConcreteReview();
    r.setRating(5);
    r.setReview("The best wax I have ever used. Hands down.");
    product.setReviews(Arrays.asList(r));
    assertTrue(dal.insertProduct(product));

    d.setCompany(partnerUserName);
    d.setProduct(product);
    d.setQuantity(quantity);
    d.setStatus("open");
    order.setDetails(Arrays.asList(d));

    assertTrue(dal.updateOrder(order));
    order = dal.getOrder(order.getOrderId());
    assertEquals(order.getDetails().get(0).getProduct().getName(), d.getProduct().getName());
    assertEquals(order.getDetails().get(0).getStatus(), d.getStatus());
    assertEquals(order.getDetails().get(0).getCompany(), d.getCompany());
    assertEquals(order.getDetails().get(0).getQuantity(), d.getQuantity());
  }

}
