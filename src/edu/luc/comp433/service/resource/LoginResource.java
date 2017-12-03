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

import edu.luc.comp433.service.representation.CustomerRepresentation;
import edu.luc.comp433.service.representation.LoginRequest;
import edu.luc.comp433.service.representation.ProtocolLink;
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
  @Consumes({ "application/luc.login+json", "application/luc.login+xml" })
  @Produces({ "application/luc.partners+json", "application/luc.customers+json", "application/luc.partners+xml",
      "application/luc.customers+xml" })
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
          CustomerRepresentation representation = customer.getCustomer(request.getUserName());
          ProtocolLink link = (ProtocolLink) context.getBean("link");
          ProtocolLink link1 = (ProtocolLink) context.getBean("link");
          ProtocolLink link2 = (ProtocolLink) context.getBean("link");
          link.setAction("PUT");
          link.setContentType("application/luc.customers+xml, application/luc.customers+json");
          link.setRel("Update customer information.");
          link.setUri("/customers");
          link1.setAction("DELETE");
          link1.setContentType("none");
          link1.setRel("Delete customer.");
          link1.setUri("/customers/" + request.getUserName());
          link2.setAction("GET");
          link2.setContentType("none");
          link2.setRel("search products");
          link2.setUri("/products/");
          representation.addLink(link);
          representation.addLink(link1);
          representation.addLink(link2);
          return Response.ok().entity(representation).build();
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
