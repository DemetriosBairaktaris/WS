package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.representation.ProtocolLink;
import edu.luc.comp433.service.workflow.PartnerActivity;
import edu.luc.comp433.service.workflow.ConcretePartnerActivity;

@Path("/partners/")
public class PartnerResource implements PartnerService {
  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private PartnerActivity activity = (ConcretePartnerActivity) context.getBean("partnerActivity");

  @GET
  @Path("/{partnerUserName}/orders")
  @Produces({ "application/json", "application/xml" })
  @Override
  public Set<OrderRepresentation> getOrdersFromPartner(@PathParam("partnerUserName") String partnerUserName) {
    System.out.println("Retrieving partner " + partnerUserName + ".");
    return new HashSet<OrderRepresentation>(activity.getOrdersFromPartner(partnerUserName));
  }

  @POST
  @Produces({ "application/json", "application/xml" })
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response insertPartner(PartnerRequest request) {
    if (!isValid(request)) {
      System.out.println("Partner request not valid to create partner.");
      return Response.status(Status.BAD_REQUEST)
          .entity("Request was not valid. Make sure partner attributes are formatted correctly.").build();
    }

    String userName = request.getUserName();
    String companyName = request.getName();
    String address = request.getAddress();
    String phone = request.getPhone();
    PartnerRepresentation representation = null;

    try {
      activity.addPartner(userName, companyName, address, phone);
      representation = activity.getPartnerByUserName(userName);
      ProtocolLink link = (ProtocolLink) context.getBean("link");
      ProtocolLink link1 = (ProtocolLink) context.getBean("link");
      ProtocolLink link2 = (ProtocolLink) context.getBean("link");
      ProtocolLink link3 = (ProtocolLink) context.getBean("link");
      link.setAction("DELETE");
      link.setContentType("none");
      link.setRel("Delete account");
      link.setUri("/partners/" + userName);
      link1.setAction("PUT");
      link1.setContentType("none");
      link1.setRel("Update name.");
      link1.setUri("/partners/" +userName + "/{newName}");
      link2.setAction("PUT");
      link2.setContentType("none");
      link2.setRel("Update address.");
      link2.setUri("/partners/" +userName + "/{newAddress}");
      link3.setAction("PUT");
      link3.setContentType("none");
      link3.setRel("Update phone.");
      link3.setUri("/partners/" +userName + "/{newPhone}");
      representation.addLink(link);
      representation.addLink(link1);
      representation.addLink(link2);
      representation.addLink(link3);
      System.out.println("Partner " + userName + " added.");
    } catch (Exception e) {
      System.out.println("Creating partner threw an error.");
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to insert partner.").build();
    }
    return Response.ok().entity(representation).build();
  }

  @PUT
  @Path("/{partnerName}/name/{companyName}")
  @Override
  public Response updateName(@PathParam(value = "partnerName") String partnerName,
      @PathParam(value = "companyName") String companyName) {
    if (!isValidPartnerUserName(partnerName)) {
      System.out.println("Invalid partner user name: " + partnerName);
      return Response.status(Status.BAD_REQUEST).entity("Invalid user name.").build();
    }

    try {
      activity.updatePartnerName(partnerName, companyName);
      System.out.println("Updated company name.");
    } catch (SQLException e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } catch (Exception e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().build();
  }

  @PUT
  @Path("/{partnerName}/address/{address}")
  @Override
  public Response updateAddress(@PathParam(value = "partnerName") String partnerName,
      @PathParam(value = "address") String address) {
    if (!isValidPartnerUserName(partnerName)) {
      System.out.println("Invalid partner user name: " + partnerName);
      return Response.status(Status.BAD_REQUEST).entity("Invalid user name.").build();
    }

    try {
      activity.updatePartnerAddress(partnerName, address);
      System.out.println("Updated company address.");
    } catch (SQLException e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } catch (Exception e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    return Response.ok().build();
  }

  @PUT
  @Path("/{partnerName}/phone/{phone}")
  @Override
  public Response updatePhone(@PathParam(value = "partnerName") String partnerName,
      @PathParam(value = "phone") String phone) {
    if (!isValidPartnerUserName(partnerName)) {
      System.out.println("Invalid partner user name: " + partnerName);
      return Response.status(Status.BAD_REQUEST).entity("Invalid user name.").build();
    }

    try {
      activity.updatePartnerPhone(partnerName, phone);
      System.out.println("Updated company phone.");
    } catch (SQLException e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } catch (Exception e) {
      System.out.println("Updating partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    return Response.ok().build();
  }

  @DELETE
  @Path("/{partnerName}")
  @Override
  public Response deletePartner(@PathParam(value = "partnerName") String partnerName) {
    if (!isValidPartnerUserName(partnerName)) {
      System.out.println("Invalid partner user name: " + partnerName);
      return Response.status(Status.BAD_REQUEST).entity("Invalid user name.").build();
    }
    try {
      activity.deletePartner(partnerName);
      System.out.println("Deleted partner: " + partnerName + ".");
    } catch (SQLException e) {
      System.out.println("Deleting partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } catch (Exception e) {
      System.out.println("Deleting partner threw an error.");
      e.printStackTrace();
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().build();
  }

  // checks if the request is valid.
  private boolean isValid(PartnerRequest request) {
    boolean result = true;
    if (!isValidPartnerUserName(request.getUserName())) {
      result = false;
    }

    return result;
  }

  // checks if the user name is valid.
  private boolean isValidPartnerUserName(String userName) {

    String pattern = "(\\w+)[@](\\w+)[.](\\w+\\S+)";
    boolean result = matchedStringRegex(userName, pattern);
    return result;
  }

  // matches a string to a regex.
  private boolean matchedStringRegex(String input, String regex) {
    boolean result = true;
    Pattern compiledPattern = Pattern.compile(regex);
    Matcher matcher = compiledPattern.matcher(input);
    if (matcher.find()) {
      if (!matcher.group(0).equals(input)) {
        result = false;
      }
    } else {
      result = false;
    }
    return result;
  }
}
