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
import edu.luc.comp433.service.workflow.DomainFacade;

//TODO FIX THIS CLASS FOR ALL ACTIVITIES

public class TestDomainActivities {

  private static ApplicationContext context;
  private CustomerActivity activity;
  private DomainFacade facade;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    ((ConfigurableApplicationContext) context).close();
  }

  @Before
  public void setUp() throws Exception {
    activity = (CustomerActivity) context.getBean("customerActivity");
    facade = (DomainFacade) context.getBean("domain");
  }

  @After
  public void tearDown() throws Exception {
    try {
      facade.deletePartner("test@email.com");
      activity.deleteCustomer("customer@email.com");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    activity = null;
    facade = null;
  }

  // TODO Figure out why this is failing and fix it.
  @Test
  public void testSearchProduct() throws SQLException, Exception {
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    ProductRepresentation product = (ProductRepresentation) context.getBean("productRepresentation");
    product.setName("test");
    product.setDesc("awesome");
    product.setCost(20);
    product.setStock(2);
    product.setCompanyUserName("test@email.com");
    ProductRepresentation retrievedProduct = facade.searchProduct("test").get(0) ; 
    assertFalse(null == retrievedProduct) ;
    assertEquals(product.getName(),retrievedProduct.getName());
    assertEquals(product.getCompanyUserName(),retrievedProduct.getCompanyUserName());
    assertEquals(product.getStock(),retrievedProduct.getStock());
    assertEquals(product.getDesc(),retrievedProduct.getDesc());
    assertEquals(product.getCost(),retrievedProduct.getCost(),0.05);
    assertTrue(facade.deletePartner("test@email.com"));
  }

  @Test
  public void testCheckAvailability() throws Exception {
    assertFalse(facade.checkAvailability("no product"));
  }

  @Test
  public void testPartnerMethods() throws SQLException, Exception {
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.updatePartnerAddress("test@email.com", "address2"));
    assertTrue(facade.updatePartnerName("test@email.com", "test2"));
    assertTrue(facade.updatePartnerPhone("test@email.com", "1234"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertNotNull(facade.getPartnerSales("test@email.com"));
  }

  @Test
  public void testCustomerMethods() throws Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "2020-01-02"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    assertTrue(activity.updateCustomerName("customer@email.com", "Jane", "Smith"));
    assertTrue(activity.updateCustomerAddress("customer@email.com", "address2"));
    assertTrue(activity.updateCustomerPhone("customer@email.com", "98765"));
    assertTrue(activity.updatePaymentInfo("customer@email.com", "Jane Smith", "9999", "007", "09-2090"));
    assertTrue(activity.deleteCustomer("customer@email.com"));
    assertFalse(activity.checkCustomerStatus("customer@email.com"));
  }

  @Test
  public void testPurchaseWorkflow() throws Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "01-2020"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(facade.buyProduct("customer@email.com", "test", 1L, 0) != -1);
    assertTrue(activity.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testFulfillmentWorkflow() throws SQLException, Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "2020-01-02"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = facade.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(facade.fulfillOrder(orderId));
    assertTrue(facade.getOrderStatus(orderId).equals("fulfilled"));
    assertTrue(facade.shipOrder(orderId));
    assertTrue(facade.getOrderStatus(orderId).equals("shipped"));
    assertTrue(activity.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testProductReview() throws Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "01-2020"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(facade.addReview("test@email.com", "test", "great", 4));
  }

  @Test
  public void testCancelOrder() throws SQLException, Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "01-2020"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = facade.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(facade.cancelOrder(orderId) != -1);
  }
}
