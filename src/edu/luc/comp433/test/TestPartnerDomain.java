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
    context = new ClassPathXmlApplicationContext("/app-context.xml");
  }

  private PartnerManager manager;
  private String userName;
  private String name;
  private String address;
  private String phone;
  private String password;

  @Before
  public void setUp() throws Exception {
    manager = (PartnerManager) context.getBean("partnerManager");
    userName = "user@email.com";
    name = "TestPartner";
    address = "312 Test St";
    phone = "555-555-5555";
    password = "root";
  }

  @After
  public void tearDown() throws Exception {
    manager.deletePartner(userName);
    manager = null;
    userName = null;
    name = null;
    address = null;
    phone = null;
    password = null;
  }

  @Test
  public void testPartnerCreate() throws SQLException, Exception {
    assertTrue(manager.createPartner(userName, name, address, phone, password));
  }

  @Test
  public void testPartnerDelete() throws SQLException, Exception {
    assertTrue(manager.createPartner(userName, name, address, phone, password));
    assertTrue(manager.deletePartner(userName));
  }

  @Test
  public void testPartnerUpdate() throws SQLException, Exception {
    assertTrue(manager.createPartner(userName, name, address, phone, password));
    assertTrue(manager.updateAddress(userName, "123 Test Rd"));
    assertTrue(manager.getPartnerProfile(userName).getAddress().equals("123 Test Rd"));
    assertTrue(manager.updateName(userName, "Test"));
    assertTrue(manager.getPartnerProfile(userName).getName().equals("Test"));
    assertTrue(manager.updatePhone(userName, "444-444-4444"));
    assertTrue(manager.getPartnerProfile(userName).getPhone().equals("444-444-4444"));
  }
}
