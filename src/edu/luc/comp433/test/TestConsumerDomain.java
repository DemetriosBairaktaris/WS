package edu.luc.comp433.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.consumer.ConsumerManager;
import edu.luc.comp433.domain.consumer.Payment;

public class TestConsumerDomain {
  
  private static ApplicationContext context;
  private ConsumerManager manager;
  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private Payment payment;
  
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
    manager = (ConsumerManager) context.getBean("consumerManager");
    userName = "user@email.com";
    firstName = "Jane";
    lastName = "Doe";
    address = "123 First St";
    phone = "123 456-7891";
    Payment test = (Payment) context.getBean("payment");
    test.setCardName("Jane Doe");
    test.setCardNumber("1234 4321 1234 4321");
    test.setCVV("123");
    payment = test;
  }
  
  @After
  public void tearDown() throws Exception {
    manager.delete(userName);
    manager = null;
    userName = null;
    firstName = null;
    lastName = null;
    address = null;
    phone = null;
    payment = null;
  }
  
  @Test
  public void testPartnerActions() throws SQLException {
    assertTrue(manager.create(userName, firstName, lastName, address, phone, payment));
    assertTrue(manager.delete(userName));
  }
  
  @Test
  public void testPartnerUpdate() throws SQLException {
    manager.create(userName, firstName, lastName, address, phone, payment);
    assertTrue(manager.updateAddress(userName, "123 Second St"));
    assertTrue(manager.getConsumer(userName).getAddress().equals("123 Second St"));
    assertTrue(manager.updatePhone(userName, "555 555-555"));
    assertTrue(manager.getConsumer(userName).getPhone().equals("555 555-5555"));
    payment.setCardName("John Doe");
    payment.setCardNumber("1111 1111 1111 1111");
    payment.setCVV("555");
    assertTrue(manager.updatePayment(userName, payment));
    assertTrue(manager.getConsumer(userName).getPayment().equals(payment));
  }
}
