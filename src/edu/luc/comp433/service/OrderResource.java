package edu.luc.comp433.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
import edu.luc.comp433.service.representation.OrderRequest;
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
	public OrderRepresentation insertOrder(OrderRequest request) throws SQLException {
		//TODO: finish
		return null; 
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
	@Override
	public void deleteOrder(@PathParam("orderId") int orderId) {
		try {
			facade.cancelOrder(orderId);
		} catch (Exception e) {
			errorCode = 400 ; 
			String message = "unable to cancel order with orderId = " + orderId;
			this.sendError(errorCode, message);
		} 
		String message = "Order cancelled" ; 
		this.sendSuccess(message);
	}

	@GET
	@Path("/partner/{partnerUserName}")
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
	
	private void sendSuccess(String message) {
		String fullMessage = "Success: " + message ; 
		try {
			response.getOutputStream().print(fullMessage);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
