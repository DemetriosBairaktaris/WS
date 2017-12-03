package edu.luc.comp433.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.LoginRequest;
import edu.luc.comp433.service.workflow.ConcreteCustomerActivity;
import edu.luc.comp433.service.workflow.ConcretePartnerActivity;
import edu.luc.comp433.service.workflow.CustomerActivity;
import edu.luc.comp433.service.workflow.PartnerActivity;

@Path("/login")
public class LoginResource implements LoginService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private CustomerActivity customer = (ConcreteCustomerActivity) context.getBean("customerActivity");
  private PartnerActivity partner = (ConcretePartnerActivity) context.getBean("partnerActivity");
  private int key = 123456789;

  @PUT
  @Consumes("application/luc.login+xml, application/luc.login+json")
  @Produces("application/luc.partners+xml, application/luc.partners+json, application/luc.customers+xml, application/luc.customers+json")
  @Override
  public Response login(LoginRequest request, @QueryParam("key") int api) {

    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }

    if (request.getUserName().isEmpty() || request.getPassword().isEmpty()) {
      System.out.println("invalid request");
      return Response.status(Status.BAD_REQUEST)
          .entity("Invalid input formatting. Ensure all fields are filled correctly").build();
    }

    if (request.getType().equals("customer")) {
      try {
        if (customer.checkLogin(request.getUserName(), request.getPassword())) {
          System.out.println("User " + request.getUserName() + " logged in.");
          return Response.ok().entity(customer.getCustomer(request.getUserName())).build();
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return Response.status(Status.BAD_REQUEST).entity("No Such User.").build();
      }
    }

    if (request.getType().equals("partner")) {
      try {
        if (partner.checkLogin(request.getUserName(), request.getPassword())) {
          System.out.println("User " + request.getUserName() + " logged in.");
          return Response.ok().entity(partner.getPartnerByUserName(request.getUserName())).build();
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return Response.status(Status.BAD_REQUEST).entity("No Such User.").build();
      }
    }

    return Response.status(Status.BAD_REQUEST).build();
  }

  private boolean checkKey(int api) {
    if (this.key == api) {
      return true;
    } else {
      return false;
    }
  }

}
