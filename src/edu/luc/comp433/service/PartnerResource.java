package edu.luc.comp433.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;
import edu.luc.comp433.service.workflow.DomainFacade;

@Path("/partners/")
public class PartnerResource implements PartnerService {
  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

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
      facade.addPartner(userName, companyName, address, phone);
      representation = facade.getPartnerByUserName(userName);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to insert partner.").build();
    }
    return Response.ok().entity(representation).build();

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
