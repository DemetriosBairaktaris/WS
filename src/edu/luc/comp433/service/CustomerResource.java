package edu.luc.comp433.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.handler.ServerHandler;
import edu.luc.comp433.service.handler.ConcreteServerHandler;
import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.workflow.ConcreteCustomerActivity;
import edu.luc.comp433.service.workflow.CustomerActivity;

@Path("/customer/")
public class CustomerResource implements CustomerService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private CustomerActivity activity = (ConcreteCustomerActivity) context.getBean("customerActivity");
  private ServerHandler handler = (ConcreteServerHandler) context.getBean("handler");

  @Context
  private HttpServletResponse response;

  public CustomerResource() {
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  @Override
  public void insertCustomer(CustomerRequest request) throws ParseException {
    if (!handler.isValid(request)) {
      System.out.println("Invalid input.");
      String message = "Unable to insert customer; user name or card number invalid.";
      handler.sendError(message, response);
    }

    String userName = request.getUserName();
    String firstName = request.getFirstName();
    String lastName = request.getLastName();
    String address = request.getAddress();
    String phone = request.getPhone();
    String cardName = request.getCardName();
    String cardNumber = request.getCardNumber();
    String cvv = request.getCvv();
    String expiration = request.getExpiration();
    SimpleDateFormat format = new SimpleDateFormat("MM-yy");
    Date date = format.parse(expiration);

    try {
      System.out.println("Creating customer...");
      activity.getCustomers().createCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv,
          date);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      String message = "Can't create customer.";
      handler.sendError(message, response);
      return;
    }
    System.out.println("Customer created successfully.");
    String message = "Customer created.";
    handler.sendSuccess(message, response);
  }
}
