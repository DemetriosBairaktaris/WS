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

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.product.ProductManager;
import edu.luc.comp433.service.facade.DomainFacade;

public class TestDomainFacade {

  private static ApplicationContext context;
  private CustomerManager customers;
  private OrderManager orders;
  private PartnerManager partners;
  private ProductManager products;
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
    customers = (CustomerManager) context.getBean("customerManager");
    orders = (OrderManager) context.getBean("orderManager");
    partners = (PartnerManager) context.getBean("partnerManager");
    products = (ProductManager) context.getBean("productManager");
    
  }

  @After
  public void tearDown() throws Exception {
    facade = null;
    customers = null;
    orders = null;
    partners = null;
    products = null;
  }

  @Test
  public void testCheckAvailability() throws SQLException {
    assertFalse(facade.checkAvailability("no product"));
  }
  
  @Test
  public void testPartnerMethods() throws SQLException, Exception {
    assertTrue(facade.addPartner("test@email.com", "test", "address", "5555"));
    assertTrue(facade.deletePartner("test@email.com"));
  }

}
