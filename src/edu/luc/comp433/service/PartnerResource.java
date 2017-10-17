package edu.luc.comp433.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.*;

import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;

@Path("/partner/")
public class PartnerResource implements PartnerService {

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
		/*
		 * String userName = request.getUserName(); 
		 * String name = request.getName();
		 * String address = request.getAddress();
		 * String phone = request.getPhone();
		 */
		// TODO: make call to activity to create partner
		// TODO: return created partner
		PartnerRepresentation partner = new PartnerRepresentation();
		partner.setUserName(request.getUserName());
		return partner;

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
