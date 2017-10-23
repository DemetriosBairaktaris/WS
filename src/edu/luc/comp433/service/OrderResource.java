package edu.luc.comp433.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;
import edu.luc.comp433.service.workflow.DomainFacade;

@Path("/order/")
public class OrderResource implements OrderService {

	private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
	private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

	@Context
	private HttpServletResponse response;
	private int errorCode;

	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public void insertOrder() {
		// TODO: update return type to orderRep
		// Implement method
	}

	@GET
	@Path("/{orderId}")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Override
	public OrderRepresentation getOrder(@PathParam("orderId") int orderId) {
		OrderRepresentation representation = facade.getOrderById(orderId);
		if (representation.getOrderId() == -1) {
			errorCode = 404;
			String message = "Could not find resource with orderId = " + orderId;
			this.sendError(errorCode, message);
		}
		return representation;
	}

	@DELETE
	@Path("/{orderId}")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public void deleteOrder() {
		// TODO: update return type
		// implement method
	}

	@GET
	@Path("/order/partner/{partnerUserName}")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@Override
	public Set<OrderRepresentation> getOrdersFromPartner(@PathParam("partnerUserName") String partnerUserName) {
		return new HashSet<OrderRepresentation>(facade.getOrdersFromPartner(partnerUserName)) ; 
	}

	private void sendError(int errorCode, String message) {
		String fullMessage = "Error: " + errorCode + " " + message;
		try {
			response.getOutputStream().print(fullMessage);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
