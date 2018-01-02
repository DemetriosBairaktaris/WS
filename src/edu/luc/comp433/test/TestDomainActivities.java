package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.workflow.CustomerActivity;
import edu.luc.comp433.service.workflow.SalesActivity;
import edu.luc.comp433.service.workflow.PartnerActivity;

public class TestDomainActivities {

  private static ApplicationContext context;
  private CustomerActivity customers;
  private PartnerActivity partners;
  private SalesActivity activity;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    context = new ClassPathXmlApplicationContext("/app-context.xml");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    ((ConfigurableApplicationContext) context).close();
  }

  @Before
  public void setUp() throws Exception {
    customers = (CustomerActivity) context.getBean("customerActivity");
    partners = (PartnerActivity) context.getBean("partnerActivity");
    activity = (SalesActivity) context.getBean("salesActivity");
  }

  @After
  public void tearDown() throws Exception {
    try {
      partners.deletePartner("test@email.com");
      customers.deleteCustomer("customer@email.com");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    customers = null;
    partners = null;
    activity = null;
  }

  @Test
  public void testSearchProduct() throws SQLException, Exception {
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    ProductRepresentation product = (ProductRepresentation) context.getBean("productRepresentation");
    product.setName("test");
    product.setDesc("awesome");
    product.setCost(20);
    product.setStock(2);
    product.setCompanyUserName("test@email.com");
    ProductRepresentation retrievedProduct = activity.searchProduct("test").get(0);
    assertFalse(null == retrievedProduct);
    assertEquals(product.getName(), retrievedProduct.getName());
    assertEquals(product.getCompanyUserName(), retrievedProduct.getCompanyUserName());
    assertEquals(product.getStock(), retrievedProduct.getStock());
    assertEquals(product.getDesc(), retrievedProduct.getDesc());
    assertEquals(product.getCost(), retrievedProduct.getCost(), 0.05);
    assertTrue(partners.deletePartner("test@email.com"));
  }

  @Test
  public void testCheckAvailability() throws Exception {
    assertFalse(activity.checkAvailability("no product"));
  }

  @Test
  public void testPartnerMethods() throws SQLException, Exception {
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(partners.updatePartnerAddress("test@email.com", "address2"));
    assertTrue(partners.updatePartnerName("test@email.com", "test2"));
    assertTrue(partners.updatePartnerPhone("test@email.com", "1234"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertNotNull(partners.getPartnerSales("test@email.com"));
  }

  @Test
  public void testCustomerMethods() throws Exception {
    assertTrue(customers.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "password", "John Doe",
        "5555", "123", "2020-01-02"));
    assertTrue(customers.checkCustomerStatus("customer@email.com"));
    assertTrue(customers.updateCustomerName("customer@email.com", "Jane", "Smith"));
    assertTrue(customers.updateCustomerAddress("customer@email.com", "address2"));
    assertTrue(customers.updateCustomerPhone("customer@email.com", "98765"));
    assertTrue(customers.updatePaymentInfo("customer@email.com", "Jane Smith", "9999", "007", "09-2090"));
    assertTrue(customers.deleteCustomer("customer@email.com"));
    assertFalse(customers.checkCustomerStatus("customer@email.com"));
  }

  @Test
  public void testPurchaseWorkflow() throws Exception {
    assertTrue(customers.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "password", "John Doe",
        "5555", "123", "01-2020"));
    assertTrue(customers.checkCustomerStatus("customer@email.com"));
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(activity.buyProduct("customer@email.com", "test", 1L, 0) != -1);
    assertTrue(customers.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testFulfillmentWorkflow() throws SQLException, Exception {
    assertTrue(customers.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "password", "John Doe",
        "5555", "123", "2020-01-02"));
    assertTrue(customers.checkCustomerStatus("customer@email.com"));
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = activity.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(activity.fulfillOrder(orderId));
    assertTrue(activity.getOrderStatus(orderId).equals("fulfilled"));
    assertTrue(activity.shipOrder(orderId));
    assertTrue(activity.getOrderStatus(orderId).equals("shipped"));
    assertTrue(customers.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testProductReview() throws Exception {
    assertTrue(customers.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "password", "John Doe",
        "5555", "123", "01-2020"));
    assertTrue(customers.checkCustomerStatus("customer@email.com"));
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(activity.addReview("test@email.com", "test", "great", 4));
  }

  @Test
  public void testCancelOrder() throws SQLException, Exception {
    assertTrue(customers.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "password", "John Doe",
        "5555", "123", "01-2020"));
    assertTrue(customers.checkCustomerStatus("customer@email.com"));
    assertTrue(partners.addPartner("test@email.com", "test", "address", "5555", "root"));
    assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = activity.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(activity.cancelOrder(orderId) != -1);
  }
}
