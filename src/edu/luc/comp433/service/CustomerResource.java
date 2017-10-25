package edu.luc.comp433.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.workflow.ConcreteCustomerActivity;
import edu.luc.comp433.service.workflow.CustomerActivity;

@Path("/customers/")
public class CustomerResource implements CustomerService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private CustomerActivity activity = (ConcreteCustomerActivity) context.getBean("customerActivity");

  @Context
  private HttpServletResponse response;

  public CustomerResource() {
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response insertCustomer(CustomerRequest request) throws ParseException {
    if (request.getUserName().isEmpty() || request.getCardNumber().isEmpty()) {
      System.out.println("Invalid input.");
      return Response.status(Status.BAD_REQUEST).entity("Invalid input formatting.").build();
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

    try {
      System.out.println("Creating customer...");
      activity.addCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv, expiration);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
    System.out.println("Customer created successfully.");
    return Response.ok().build();
  }
}
