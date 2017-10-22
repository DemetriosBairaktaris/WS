package edu.luc.comp433.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;
import edu.luc.comp433.service.workflow.DomainFacade;

@Path("/partner/")
public class PartnerResource implements PartnerService {
	private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
	private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

	@POST
	@Produces({ "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@Override
	public PartnerRepresentation insertPartner(PartnerRequest request) {
		if (!isValid(request)) {
			// TODO probably don't wanna return null, gotta figure out how
			// to manipulate the header to give error.
			return null;
		}

		String userName = request.getUserName();
		String companyName = request.getName();
		String address = request.getAddress();
		String phone = request.getPhone();
		PartnerRepresentation representation = null;
		
		try {
			//for testing purposes 
			facade.deletePartner(userName);
			facade.addPartner(userName, companyName, address, phone);
			representation = facade.getPartnerByUserName(userName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return representation;

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
