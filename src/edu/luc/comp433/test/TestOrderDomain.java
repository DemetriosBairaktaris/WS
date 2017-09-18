package edu.luc.comp433.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

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

public class TestOrderDomain {

  private static ApplicationContext context;
  private OrderManager manager;
  private double id;
  private Date timestamp;
  private String status;
  private double detailID;
  private Product product;
  private long quantity;
  private String detailStatus;
  
  @BeforeClass
  public static void setUpClass() {
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  }
  
  @AfterClass
  public static void afterClass() {
    ((ConfigurableApplicationContext)context).close();
  }
  
  @Before
  public void setUp() throws Exception {
    manager = (OrderManager) context.getBean("orderManager");
    id = 0;
    timestamp = new Date(System.currentTimeMillis());
    status = "open";
    detailID = 0;
    product = (Product) context.getBean("product");
    product.setName("Test");
    product.setCost(20);
    product.setStock(5);
    product.setDesc("test");
    product.setCompany("testing");
    quantity = 2;
    detailStatus = "open";
  }
  
  @After
  public void tearDown() throws Exception {
    manager.removeOrder(0);
    manager = null;
    id = 0;
    timestamp = null;
    status = null;
    detailID = 0;
    product = null;
    quantity = 0;
    detailStatus = null;
  }
  
  @Test
  public void testOrderActions() {
    assertTrue(manager.createOrderDetail(detailID, product, quantity, detailStatus, 0));
    assertTrue(manager.createOrder(id, timestamp));
    assertTrue(manager.removeOrderDetail(detailID, id));
    assertTrue(manager.closeOrder(id));
  }
  
  @Test
  public void testOrderUpdate() {
    assertTrue(manager.updateOrder("shipped", id));
    assertTrue(manager.getOrder(id).getStatus().equals("shipped"));
  }
  
  @Test
  public void testOrderDetailUpdate() {
    assertTrue(manager.updateOrderDetail("fulfilled", detailID, id));
    assertTrue(manager.getOrder(id).getDetails().get((int) detailID).getStatus().equals("fulfilled"));
    assertTrue(manager.updateOrderDetailQuantity(3, detailID, id));
    assertTrue(manager.getOrder(id).getDetails().get((int) detailID).getQuantity() == 3);
  }

}
