package edu.luc.comp433.test;

import static org.junit.Assert.assertTrue;

import edu.luc.comp433.domain.customer.CustomerManager;

import java.sql.SQLException;

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
  private String cardName;
  private String cardNumber;
  private String cvv;

  @BeforeClass
  public static void setUpClass() {
    context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  }

  @AfterClass
  public static void afterClass() {
    ((ConfigurableApplicationContext) context).close();
  }

  /**
   * Creates the manager and populates the info for a customer.
   * @throws Exception thrown if Spring or SQL have errors.
   */
  @Before
  public void setUp() throws Exception {
    manager = (CustomerManager) context.getBean("customerManager");
    userName = "user@email.com";
    firstName = "Jane";
    lastName = "Doe";
    address = "123 First St";
    phone = "123 456-7891";
    cardName = "Jane Doe";
    cardNumber = "1234 4321 1234 4321";
    cvv = "123";
  }

  /**
   * Removes all configurations.
   * @throws Exception thrown if Spring or SQL have errors.
   */
  @After
  public void tearDown() throws Exception {
    manager = null;
    userName = null;
    firstName = null;
    lastName = null;
    address = null;
    phone = null;
    cardName = null;
    cardNumber = null;
    cvv = null;
  }

  @Test
  public void testPartnerActions() throws SQLException {
    assertTrue(
        manager.create(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv));
    assertTrue(manager.delete(userName));
  }

  @Test
  public void testPartnerUpdate() throws SQLException {
    manager.create(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv);
    assertTrue(manager.updateAddress(userName, "123 Second St"));
    assertTrue(manager.getCustomer(userName).getAddress().equals("123 Second St"));
    assertTrue(manager.updatePhone(userName, "555 555-5555"));
    assertTrue(manager.getCustomer(userName).getPhone().equals("555 555-5555"));
    assertTrue(manager.updatePayment(userName, "John Doe", "5555 5555 5555 5555", "000"));
    assertTrue(
        manager.getCustomer(userName).getPayment().getCardNumber().equals("5555 5555 5555 5555"));
    assertTrue(manager.delete(userName));
  }
}
