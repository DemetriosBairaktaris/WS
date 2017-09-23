package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.customer.Payment;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.product.Product;

public class testOrderDomain {

  private static ApplicationContext context;
  private OrderManager orders;
  private CustomerManager customerManager ; 
  private String status;
  private String customerUserName;
  private Customer customer ; 
  private Product product;
  private Payment payment ; 
  private long quantity;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    ((ConfigurableApplicationContext) context).close();
  }

  @Before
  public void setUp() throws Exception {
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
    orders = (OrderManager) context.getBean("orderManager");
    customerManager = (CustomerManager) context.getBean("customerManager");
    status = "fulfilled";
    
    customerUserName = "user@test.com";
    customer = (Customer) context.getBean("customer");
    customer.setUserName(customerUserName);
    customer.setFirstName("John");
    customer.setLastName("Manson");
    customer.setPhone("888-909-8758");
    customer.setAddress("12 parkway st");
    payment = (Payment) context.getBean("payment");
    payment.setCardName("visa");
    payment.setCvv("344");
    payment.setCardNumber("222222222222");
    payment.setExpiration(Date.valueOf("2018-2-3"));
    customer.setPayment(payment);
    
    product = (Product) context.getBean("product");
    product.setCompanyUserName("BIGDADDY@GMAIL.COM");
    product.setCost(50);
    product.setDesc("awesome");
    product.setName("Thing");
    product.setStock(2L);
    quantity = 1L;
    
    customerManager.createCustomer(customer.getUserName(),
    		customer.getFirstName(),
    		customer.getLastName(),
    		customer.getAddress(),
    		customer.getPhone(),
    		customer.getPayment().getCardName(),
    		customer.getPayment().getCardNumber(),
    		customer.getPayment().getCvv(),
    		customer.getPayment().getExpiration()) ; 
  }

  @After
  public void tearDown() throws Exception {
    orders = null;
    status = null;
    customer = null;
    product = null;
    quantity = 0;
    customerManager.deleteCustomer(customer.getUserName());
  }

  @Test
  public void testCreateAndCancelOrder() throws SQLException {
    int orderId = orders.createOrder(customer.getUserName());
    assertTrue(orderId > 0) ; 
    //assertNotNull(orders.getOrder(orderId));
    assertTrue(orders.cancelOrder(orderId));
  }

  @Test
  public void testOrderActions() throws SQLException {
    int orderId = orders.createOrder(customerUserName);
    assertTrue(orders.createOrderDetail(orderId, product, quantity));
    assertTrue(
        orders.getOrder(orderId)
        .getDetails()
        .get(0)
        .getCompany()
        .equals(product.getCompanyUserName()));
    assertTrue(orders.fulfillOrder(orderId));
    assertTrue(orders.getOrder(orderId).getStatus().equals(status));
    assertTrue(orders.shipOrder(orderId));
    assertTrue(orders.getOrder(orderId).getStatus().equals("shipped"));
    assertTrue(orders.cancelOrder(orderId));
    assertTrue(orders.getOrder(orderId).equals(null));
  }

}
