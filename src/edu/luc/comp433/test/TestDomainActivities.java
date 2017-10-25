package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.workflow.CustomerActivity;

//TODO FIX THIS CLASS FOR ALL ACTIVITIES

public class TestDomainActivities {

  private static ApplicationContext context;
  private CustomerActivity activity;

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

  }

  @After
  public void tearDown() throws Exception {
    try {
      // activity.deletePartner("test@email.com");
      activity.deleteCustomer("customer@email.com");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    activity = null;

  }

  @Test
  public void testSearchProduct() throws SQLException, Exception {
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    List<String> product = new ArrayList<String>();
    product.add("test");
    product.add("awesome");
    product.add(Double.toString(20d));
    product.add(Long.toString(2L));
    // TODO fix this for the new code
    // if (product.get(0).equals(facade.searchProduct("test").get(0))) {
    // } else {
    // fail();
    // }
    // assertTrue(activity.deletePartner("test@email.com"));
  }

  @Test
  public void testCheckAvailability() throws Exception {
    // assertFalse(activity.checkAvailability("no product"));
  }

  @Test
  public void testPartnerMethods() throws SQLException, Exception {
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.updatePartnerAddress("test@email.com", "address2"));
    // assertTrue(activity.updatePartnerName("test@email.com", "test2"));
    // assertTrue(activity.updatePartnerPhone("test@email.com", "1234"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    // assertNotNull(activity.getPartnerSales("test@email.com"));
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
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    // assertTrue(activity.buyProduct("customer@email.com", "test", 1L, 0) != -1);
    assertTrue(activity.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testFulfillmentWorkflow() throws SQLException, Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "2020-01-02"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    // int orderId = activity.buyProduct("customer@email.com", "test", 1L, 0);
    // assertTrue(activity.fulfillOrder(orderId));
    // assertTrue(activity.getOrderStatus(orderId).equals("fulfilled"));
    // assertTrue(activity.shipOrder(orderId));
    // assertTrue(activity.getOrderStatus(orderId).equals("shipped"));
    // assertTrue(activity.deleteCustomer("customer@email.com"));
  }

  @Test
  public void testProductReview() throws Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "01-2020"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    // assertTrue(activity.addReview("test@email.com", "test", "great", 4));
  }

  @Test
  public void testCancelOrder() throws SQLException, Exception {
    assertTrue(activity.addCustomer("customer@email.com", "John", "Doe", "address", "12345", "John Doe", "5555", "123",
        "01-2020"));
    assertTrue(activity.checkCustomerStatus("customer@email.com"));
    // assertTrue(activity.addPartner("test@email.com", "test", "address", "5555"));
    // assertTrue(activity.acceptPartnerProduct("test@email.com", "test", "awesome",
    // 20d, 2L));
    // int orderId = activity.buyProduct("customer@email.com", "test", 1L, 0);
    // assertTrue(activity.cancelOrder(orderId) != -1);
  }
}
