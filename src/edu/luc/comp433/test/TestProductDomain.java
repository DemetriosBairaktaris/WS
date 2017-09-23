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
  private String companyUserName;

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
    companyUserName ="BIGDADDY@GMAIL.COM";
  }

  @After
  public void tearDown() throws Exception {
    products.deleteProduct(companyUserName, name);
    products = null;
    name = null;
    desc = null;
    cost = 0;
    stock = 0;
  }

  @Test
  public void testProductCreate() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyUserName));
  }

  @Test
  public void testProductDelete() throws SQLException {
    assertTrue(products.addProduct(name, desc, cost, stock, companyUserName));
    assertTrue(products.deleteProduct(companyUserName, name));
  }

  @Test
  public void testProductUpdates() throws Exception {
    assertTrue(products.addProduct(name, desc, cost, stock, companyUserName));
    assertTrue(products.updateCost(companyUserName, name, 50d));
    assertTrue(products.getProductFromPartner(name,companyUserName).getCost() == 50d);
    assertTrue(products.updateStock(companyUserName, name, 2L));
    assertTrue(products.getProducts(name).get(0).getStock() == 2L);
  }

  @Test
  public void testProductReview() throws Exception {
    assertTrue(products.addProduct(name, desc, cost, stock, companyUserName));
    Review review = (Review) context.getBean("review");
    review.setRating(5);
    review.setReview("Awesome");
    assertTrue(products.addReview(companyUserName, name, review));
    assertTrue(products.getReviews(companyUserName, name).get(0).getReview().equals(review.getReview()));
  }
  
  @Test
  public void testGetProductsForCompany() {
    fail("Not Yet Implemented");
  }

}
