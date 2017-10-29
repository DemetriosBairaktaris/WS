package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.workflow.PartnerActivity;
import edu.luc.comp433.service.workflow.ConcretePartnerActivity;

@Path("/partners/")
public class PartnerResource implements PartnerService {
  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private PartnerActivity activity = (ConcretePartnerActivity) context.getBean("partnerActivity");

  @POST
  @Produces({ "application/json", "application/xml" })
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response insertPartner(PartnerRequest request) {
    if (!isValid(request)) {
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to insert partner.").build();
    }
    return Response.ok().entity(representation).build();
  }

  @DELETE
  @Path("/{partnerName}")
  @Override
  public Response deletePartner(@PathParam(value = "partnerName") String partnerName) {
    if (!isValidPartnerUserName(partnerName)) {
      return Response.status(Status.BAD_REQUEST).entity("Invalide user name.").build();
    }
    try {
      activity.deletePartner(partnerName);
    } catch (SQLException e) {
      e.printStackTrace();
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().build();
  }

  private boolean isValid(PartnerRequest request) {
    boolean result = true;
    if (!isValidPartnerUserName(request.getUserName())) {
      result = false;
    }

    return result;
  }

  private boolean isValidPartnerUserName(String userName) {

    String pattern = "(\\w+)[@](\\w+)[.](\\w+\\S+)";
    boolean result = matchedStringRegex(userName, pattern);
    return result;
  }

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
