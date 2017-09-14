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

public class TestOrderDomain {

  private static ApplicationContext context;
  private OrderManager manager;
  
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
  }
  
  @After
  public void tearDown() throws Exception {
    manager = null;
  }
  
  @Test
  public void testCreateOrder() {
    assertTrue(manager.createOrder());
  }

}
