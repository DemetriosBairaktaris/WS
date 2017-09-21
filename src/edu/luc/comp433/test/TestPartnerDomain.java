package edu.luc.comp433.test;

import static org.junit.Assert.assertTrue;

import edu.luc.comp433.domain.partner.PartnerManager;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPartnerDomain {

  private static ApplicationContext context;

  @AfterClass
  public static void afterClass() {
    ((ConfigurableApplicationContext) context).close();
  }

  @BeforeClass
  public static void setUpClass() {
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  }

  private PartnerManager manager;
  private String userName;
  private String name;

  private String address;

  private String phone;

  /**
   * Sets up the manager and populates the attributes for the partner.
   * 
   * @throws Exception
   *           thrown if Spring or SQL do not work.
   */
  @Before
  public void setUp() throws Exception {
    manager = (PartnerManager) context.getBean("partnerManager");
    userName = "user@email.com";
    name = "TestPartner";
    address = "312 Test St";
    phone = "555-555-5555";
  }

  /**
   * Removes all items.
   * 
   * @throws Exception
   *           thrown if Spring or SQL do not work.
   */
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
  public void testPartnerProducts() throws SQLException, Exception {
    manager.create(userName, name, address, phone);
    assertTrue(manager.addProduct(userName, "product", "its great", 20.00, 2));
    assertTrue(manager.getProduct("product",userName).getName().equals("product"));
  }

  @Test
  public void testPartnerUpdate() {
    manager.create(userName, name, address, phone);
    assertTrue(manager.updateAddress(userName, "123 Test Rd"));
    assertTrue(manager.getPartnerProfile(userName).getAddress().equals("123 Test Rd"));
    assertTrue(manager.updateName(userName, "Test"));
    assertTrue(manager.getPartnerProfile(userName).getName().equals("Test"));
    assertTrue(manager.updatePhone(userName, "444-444-4444"));
    assertTrue(manager.getPartnerProfile(userName).getPhone().equals("444-444-4444"));
  }
}
