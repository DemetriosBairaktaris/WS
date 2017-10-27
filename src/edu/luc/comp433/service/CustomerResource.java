package edu.luc.comp433.service;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.CustomerRepresentation;
import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.workflow.ConcreteCustomerActivity;
import edu.luc.comp433.service.workflow.CustomerActivity;

@Path("/customers/")
public class CustomerResource implements CustomerService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private CustomerActivity activity = (ConcreteCustomerActivity) context.getBean("customerActivity");

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

  @GET
  @Path("/{userName}/status")
  @Override
  public Response getCustomerStatus(@PathParam(value = "userName") String userName) throws SQLException {
    if (activity.checkCustomerStatus(userName)) {
      return Response.ok().entity("Customer exists and is active.").build();
    }
    return Response.status(Status.BAD_REQUEST).entity("User does not exist.").build();
  }

  @GET
  @Path("/{userName}")
  @Produces({ "application/json", "application/xml" })
  @Override
  public Response getCustomer(@PathParam(value = "userName") String userName) throws SQLException {
    if (activity.checkCustomerStatus(userName)) {
      CustomerRepresentation customer = (CustomerRepresentation) context.getBean("customerRepresentation");
      customer.setUserName(activity.getCustomers().getCustomer(userName).getUserName());
      customer.setFirstName(activity.getCustomers().getCustomer(userName).getFirstName());
      customer.setLastName(activity.getCustomers().getCustomer(userName).getLastName());
      customer.setAddress(activity.getCustomers().getCustomer(userName).getAddress());
      customer.setPhone(activity.getCustomers().getCustomer(userName).getPhone());
      customer.setCardName(activity.getCustomers().getCustomer(userName).getPayment().getCardName());
      customer.setCardNumber(activity.getCustomers().getCustomer(userName).getPayment().getCardNumber());
      customer.setCvv(activity.getCustomers().getCustomer(userName).getPayment().getCvv());
      customer.setExpiration(activity.getCustomers().getCustomer(userName).getPayment().getExpiration().toString());
      return Response.ok().entity(customer).build();
    }
    return Response.status(Status.BAD_REQUEST).entity("User not found.").build();
  }

  @DELETE
  @Path("/{userName}")
  @Override
  public Response deleteCustomer(@PathParam(value = "userName") String userName) throws SQLException {
    if (activity.deleteCustomer(userName)) {
      return Response.ok().build();
    }
    return Response.status(Status.BAD_REQUEST).entity("Cannot delete customer.").build();
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response updateCustomer(CustomerRequest request) throws ParseException {
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
      System.out.println("Updating customer...");
      activity.updateCustomerAddress(userName, address);
      activity.updateCustomerName(userName, firstName, lastName);
      activity.updateCustomerPhone(userName, phone);
      activity.updatePaymentInfo(userName, cardName, cardNumber, cvv, expiration);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
    System.out.println("Customer updated successfully.");
    return Response.ok().entity("Customer updated.").build();
  }
}
