package edu.luc.comp433.test;

import edu.luc.comp433.domain.customer.CustomerManager;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCustomerDomain {

  private static ApplicationContext context;
  private CustomerManager manager;
  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private String password;
  private String cardName;
  private String cardNumber;
  private String cvv;
  private Date expiration;

  @BeforeClass
  public static void setUpClass() {
    context = new ClassPathXmlApplicationContext("/app-context.xml");
  }

  @AfterClass
  public static void afterClass() {
    ((ConfigurableApplicationContext) context).close();
  }

  @Before
  public void setUp() throws Exception {
    manager = (CustomerManager) context.getBean("customerManager");
    userName = "user@email.com";
    firstName = "Jane";
    lastName = "Doe";
    address = "123 First St";
    phone = "123 456-7891";
    password = "password";
    cardName = "Jane Doe";
    cardNumber = "1234 4321 1234 4321";
    cvv = "123";
    expiration = new Date(1589518800000L);
  }

  @After
  public void tearDown() throws Exception {
    // manager.deleteCustomer(userName);
    manager = null;
    userName = null;
    firstName = null;
    lastName = null;
    address = null;
    phone = null;
    password = null;
    cardName = null;
    cardNumber = null;
    cvv = null;
    expiration = null;

  }

  @Test
  public void testCustomerCreate() throws SQLException {
    assertTrue(manager.createCustomer(userName, firstName, lastName, address, phone, password, cardName, cardNumber,
        cvv, expiration));
    assertTrue(manager.deleteCustomer(userName));
  }

  @Test
  public void testCustomerDelete() throws SQLException {
    assertTrue(manager.createCustomer(userName, firstName, lastName, address, phone, password, cardName, cardNumber,
        cvv, expiration));
    assertTrue(manager.deleteCustomer(userName));
  }

  @Test
  public void testCustomerUpdate() throws SQLException {
    assertTrue(manager.createCustomer(userName, firstName, lastName, address, phone, password, cardName, cardNumber,
        cvv, expiration));
    assertTrue(manager.updateAddress(userName, "123 Second St"));
    assertNotNull(manager.getCustomer(userName));
    assertTrue(manager.getCustomer(userName).getAddress().equals("123 Second St"));
    assertTrue(manager.updatePhone(userName, "555 555-5555"));
    assertTrue(manager.getCustomer(userName).getPhone().equals("555 555-5555"));
    assertTrue(manager.updatePayment(userName, "John Doe", "5555 5555 5555 5555", "000", new Date(1747285200000L)));
    assertTrue(manager.getCustomer(userName).getPayment().getCardNumber().equals("5555 5555 5555 5555"));
    assertTrue(manager.deleteCustomer(userName));
  }
}
