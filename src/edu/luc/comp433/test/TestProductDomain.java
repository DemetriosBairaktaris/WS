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
  }
  
  @After
  public void tearDown() throws Exception {
    manager = null;
  }
  
    @Test
    public void testAddProduct() {
      assertTrue(manager.addProduct("Pens", "Black Ink"));
    }

}
