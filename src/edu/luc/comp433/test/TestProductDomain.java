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

import edu.luc.comp433.domain.product.ProductManager;

public class TestProductDomain {

  private static ApplicationContext context;
  private ProductManager manager;
  private String name;
  private String desc;
  private double cost;
  private String company;
  private long stock;
  
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
    manager = (ProductManager) context.getBean("productManager");
    name = "Pens";
    desc = "Black Ink";
    cost = 50d;
    company = "Pens Galore";
    stock = 10l;
  }
  
  @After
  public void tearDown() throws Exception {
    manager.removeProduct(name);
    manager = null;
  }
  
  @Test
  public void testProductActions() {
    assertTrue(manager.addProduct(name, desc, cost, company, stock));
    assertTrue(manager.removeProduct(name));
  }
  
  @Test
  public void testProductUpdates() {
    manager.addProduct(name, desc, cost, company, stock);
    assertTrue(manager.updateDescription("Blue Ink", name));
    assertTrue(manager.getProduct(name).getDesc().equals("Blue Ink"));
    assertTrue(manager.updateCost(30d, name));
    assertTrue(manager.getProduct(name).getCost() == 30d);
    assertTrue(manager.updateStock(5l, name));
    assertTrue(manager.getProduct(name).getStock() == 5l);
  }
}
