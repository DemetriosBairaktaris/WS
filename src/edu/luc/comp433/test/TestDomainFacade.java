package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
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

import edu.luc.comp433.service.facade.DomainFacade;

public class TestDomainFacade {

  private static ApplicationContext context;
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
    facade = (DomainFacade) context.getBean("domain");
    

  }

  @After
  public void tearDown() throws Exception {
	 try {
		facade.deletePartner("test@email.com") ; 
		facade.deleteCustomer("customer@email.com");
	  }catch(Exception e) {
		  System.out.println(e.getMessage());
	  }
    facade = null;
    
  }

  @Test
  public void testSearchProduct() throws SQLException, Exception {
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    List<String> product = new ArrayList<String>();
    product.add("test");
    product.add("awesome");
    product.add(Double.toString(20d));
    product.add(Long.toString(2L));
    if (product.get(0).equals(facade.searchProduct("test").get(0))) {
    } else {
      fail();
    }
    assertTrue(facade.deletePartner("test@email.com")) ; 
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
    assertTrue(facade.addCustomer("customer@email.com", "John", "Doe", "address", "12345",
        "John Doe", "5555", "123", "2020-01-02"));
    assertTrue(facade.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.updateCustomerName("customer@email.com", "Jane", "Smith"));
    assertTrue(facade.updateCustomerAddress("customer@email.com", "address2"));
    assertTrue(facade.updateCustomerPhone("customer@email.com", "98765"));
    assertTrue(
        facade.updatePaymentInfo("customer@email.com", "Jane Smith", "9999", "007", "09-2090"));
    assertTrue(facade.deleteCustomer("customer@email.com"));
    assertFalse(facade.checkCustomerStatus("customer@email.com"));
  }

  @Test
  public void testPurchaseWorkflow() throws Exception {
    assertTrue(facade.addCustomer("customer@email.com", "John", "Doe", "address", "12345",
        "John Doe", "5555", "123", "01-2020"));
    assertTrue(facade.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(facade.buyProduct("customer@email.com", "test", 1L, 0) != -1);
    assertTrue(facade.deleteCustomer("customer@email.com"));
    //TODO add proper cleanup methods here
  }
  
  @Test
  public void testFulfillmentWorkflow() throws SQLException, Exception {
    assertTrue(facade.addCustomer("customer@email.com", "John", "Doe", "address", "12345",
        "John Doe", "5555", "123", "2020-01-02"));
    assertTrue(facade.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = facade.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(facade.fulfillOrder(orderId));
    assertTrue(facade.getOrderStatus(orderId).equals("fulfilled"));
    assertTrue(facade.shipOrder(orderId));
    assertTrue(facade.getOrderStatus(orderId).equals("shipped"));
    assertTrue(facade.deleteCustomer("customer@email.com"));
    //TODO add proper cleanup methods here
  }

  @Test
  public void testProductReview() throws Exception {
    assertTrue(facade.addCustomer("customer@email.com", "John", "Doe", "address", "12345",
        "John Doe", "5555", "123", "01-2020"));
    assertTrue(facade.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    assertTrue(facade.addReview("test@email.com", "test", "great", 4));
  }
  
  @Test
  public void testCancelOrder() throws SQLException, Exception {
    assertTrue(facade.addCustomer("customer@email.com", "John", "Doe", "address", "12345",
        "John Doe", "5555", "123", "01-2020"));
    assertTrue(facade.checkCustomerStatus("customer@email.com"));
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.acceptPartnerProduct("test@email.com", "test", "awesome", 20d, 2L));
    int orderId = facade.buyProduct("customer@email.com", "test", 1L, 0);
    assertTrue(facade.cancelOrder(orderId) != -1);
  }
}
