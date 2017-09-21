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

import edu.luc.comp433.domain.product.ProductManager;
import edu.luc.comp433.domain.product.Review;

public class TestProductDomain {

  private static ApplicationContext context;
  private ProductManager products;
  private String name;
  private String desc;
  private double cost;
  private long stock;
  private String companyName;

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
    products = (ProductManager) context.getBean("productManager");
    name = "Test Product";
    desc = "Awesome Test Product";
    cost = 99d;
    stock = 5L;
    companyName ="MyCompany";
  }

  @After
  public void tearDown() throws Exception {
    products.deleteProduct(name);
    products = null;
    name = null;
    desc = null;
    cost = 0;
    stock = 0;
  }

  @Test
  public void testProductCreate() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyName));
  }

  @Test
  public void testProductDelete() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyName));
    assertTrue(products.deleteProduct(name));
  }

  @Test
  public void testProductUpdates() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyName));
    assertTrue(products.updateCost(name, 50d));
    assertTrue(products.getProduct(name).getCost() == 50d);
    assertTrue(products.updateStock(name, 2L));
    assertTrue(products.getProduct(name).getStock() == 2L);
  }

  @Test
  public void testProductReview() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyName));
    Review review = (Review) context.getBean("review");
    review.setRating(5);
    review.setReview("Awesome");
    assertTrue(products.addReview(name, review));
    assertTrue(products.getReviews(name).get(0).equals(review));
  }
  
  @Test
  public void testGetProductsForCompany() {
    fail("Not Yet Implemented");
  }

}
