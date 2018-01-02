package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Arrays;

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
import edu.luc.comp433.domain.product.ProductManager;

public class TestOrderDomain {

  private static ApplicationContext context;
  private OrderManager orders;
  private CustomerManager customerManager;
  private ProductManager productManager;
  private String status;
  private String customerUserName;
  private Customer customer;
  private Product product;
  private Payment payment;
  private long quantity;

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
    context = new ClassPathXmlApplicationContext("/app-context.xml");
    orders = (OrderManager) context.getBean("orderManager");
    customerManager = (CustomerManager) context.getBean("customerManager");
    productManager = (ProductManager) context.getBean("productManager");
    status = "fulfilled";

    customerUserName = "user@test.com";
    customer = (Customer) context.getBean("customer");
    customer.setUserName(customerUserName);
    customer.setFirstName("John");
    customer.setLastName("Manson");
    customer.setPhone("888-909-8758");
    customer.setPassword("root");
    customer.setAddress("12 parkway st");
    payment = (Payment) context.getBean("payment");
    payment.setCardName("visa");
    payment.setCvv("344");
    payment.setCardNumber("222222222222");
    payment.setExpiration(Date.valueOf("2018-2-3"));
    customer.setPayment(payment);
    product = (Product) context.getBean("product");
    product.setCompanyUserName("management@target.com");
    product.setCost(50);
    product.setDesc("awesome");
    product.setName("Thing");
    product.setStock(2L);
    productManager.addProduct(product.getName(), product.getDesc(), product.getCost(), product.getStock(),
        product.getCompanyUserName());
    quantity = 1L;

    customerManager.createCustomer(customer.getUserName(), customer.getFirstName(), customer.getLastName(),
        customer.getAddress(), customer.getPhone(), customer.getPassword(), customer.getPayment().getCardName(),
        customer.getPayment().getCardNumber(), customer.getPayment().getCvv(), customer.getPayment().getExpiration());
  }

  @After
  public void tearDown() throws Exception {
    customerManager.deleteCustomer(customer.getUserName());
    productManager.deleteProduct(product.getCompanyUserName(), product.getName());
    orders = null;
    status = null;
    customer = null;
    product = null;
    quantity = 0;

  }

  @Test
  public void testCreateAndCancelOrder() throws Exception {
    int orderId = orders.createOrder(customer.getUserName());
    assertTrue(orderId > 0);
    assertNotNull(orders.getOrder(orderId));
    assertTrue(orders.getOrder(orderId).getOrderId() > 0);
    assertEquals(orders.getOrder(orderId).getDetails(), Arrays.asList());
    assertTrue(orders.cancelOrder(orderId));
  }

  @Test
  public void testOrderActions() throws Exception {
    int orderId = orders.createOrder(customerUserName);
    assertTrue(orders.createOrderDetail(orderId, product, quantity));
    assertTrue(orders.getOrder(orderId).getDetails().get(0).getCompany().equals(product.getCompanyUserName()));

    assertTrue(orders.fulfillOrder(orderId));
    assertEquals(orders.getOrder(orderId).getStatus(), status);
    assertTrue(orders.shipOrder(orderId));
    assertTrue(orders.getOrder(orderId).getStatus().equals("shipped"));
    assertTrue(orders.cancelOrder(orderId));
    assertNull(orders.getOrder(orderId));
  }

}
