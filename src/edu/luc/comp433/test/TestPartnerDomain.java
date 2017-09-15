package edu.luc.comp433.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.partner.PartnerManager;

public class TestPartnerDomain {

  private static ApplicationContext context;
  private PartnerManager manager;
  
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
    manager = (PartnerManager) context.getBean("partnerManager");
  }
  
  @After
  public void tearDown() throws Exception {
    manager = null;
  }
  
  @Test
  public void testPartnerActions() { 
    assertTrue(manager.create("user@email.com", "TestPartner", "312 Test St", "555-555-5555"));
  }
  
  @Test
  public void testPartnerDeletion() {
    assertTrue(manager.delete("user@email.com"));
  }
}
