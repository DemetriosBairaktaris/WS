package edu.luc.comp433.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.product.Product;

public class testOrderDomain {

  private static ApplicationContext context;
  private OrderManager orders;
  private String status;
  private String customer;
  private Product product;
  private long quantity;

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
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
    orders = (OrderManager) context.getBean("orderManager");
    status = "fulfilled";
    customer = "user@test.com";
    product = (Product) context.getBean("product");
    product.setCompanyName("company");
    product.setCost(50);
    product.setDesc("awesome");
    product.setName("Thing");
    product.setStock(2L);
    quantity = 1L;
  }

  @After
  public void tearDown() throws Exception {
    orders = null;
    status = null;
    customer = null;
    product = null;
    quantity = 0;
  }

  @Test
  public void testCreateAndCancelOrder() {
    int orderId = orders.createOrder(customer);
    assertFalse(orders.getOrder(orderId).equals(null));
    assertTrue(orders.cancelOrder(orderId));
  }

  @Test
  public void testOrderActions() {
    int orderId = orders.createOrder(customer);
    assertTrue(orders.createOrderDetail(orderId, product, quantity));
    assertTrue(
        orders.getOrder(orderId).getDetails().get(0).getCompany().equals(product.getCompanyName()));
    assertTrue(orders.fulfillOrder(orderId));
    assertTrue(orders.getOrder(orderId).getStatus().equals(status));
    assertTrue(orders.shipOrder(orderId));
    assertTrue(orders.getOrder(orderId).getStatus().equals("shipped"));
    assertTrue(orders.cancelOrder(orderId));
    assertTrue(orders.getOrder(orderId).equals(null));
  }

}
