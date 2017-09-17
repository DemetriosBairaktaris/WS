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
  private String userName;
  private String name;
  private String address;
  private String phone;
  
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
    userName = "user@email.com";
    name = "TestPartner";
    address = "312 Test St";
    phone = "555-555-5555";
  }
  
  @After
  public void tearDown() throws Exception {
    manager.delete(userName);
    manager = null;
    userName = null;
    name = null;
    address = null;
    phone = null;
  }
  
  @Test
  public void testPartnerActions() { 
    assertTrue(manager.create(userName, name, address, phone));
    assertTrue(manager.delete(userName));
  }
  
  @Test
  public void testPartnerUpdate() {
    manager.create(userName, name, address, phone);
    assertTrue(manager.updateAddress(userName, "123 Test Rd"));
    assertTrue(manager.getPartnerProfile(userName).getAddress().equals("123 Test Rd"));
    assertTrue(manager.updateName(userName, "Test"));
    assertTrue(manager.getPartnerProfile(userName).getName().equals("Test"));
    assertTrue(manager.updatePhone(userName, "444-444-4444"));
    assertTrue(manager.getPartnerProfile(userName).getPhone().equals("444-444-4444") );
  }
}
