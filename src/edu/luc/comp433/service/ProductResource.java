package edu.luc.comp433.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;
import edu.luc.comp433.service.workflow.DomainFacade;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;

@Path("/product/")
public class ProductResource implements ProductService {

	private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
	private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

	@Context
	private HttpServletResponse response;
	private int errorCode;

	@GET
	@Path("/{productName}")
	@Produces({ "application/json", "application/xml" })
	@Override
	public Set<ProductRepresentation> getProduct(@PathParam("productName") String productName)
			throws SQLException, Exception {
		System.out.println("Received GET request to search products using parameter \"" + productName + ".\"");
		List<ProductRepresentation> list = facade.searchProduct(productName);
		HashSet<ProductRepresentation> products = new HashSet<ProductRepresentation>(list);
		return products;
	}

	@POST
	@Produces({ "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@Override
	public ProductRepresentation insertProduct(ProductRequest request) {
		if (!isValid(request)) {
			errorCode = 400;
			String message = "unable to insert product with name = " + request.getName();
			this.sendError(errorCode, message);
		}
		String name = request.getName();
		String companyUserName = request.getCompanyUserName();
		String desc = request.getDesc();
		long stock = request.getStock();
		float cost = (float) request.getCost();

		ProductRepresentation representation = new ProductRepresentation();

		try {
			facade.getProducts().addProduct(name, desc, cost, stock, companyUserName);
			representation = facade.getProductFromPartner(name, companyUserName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorCode = 500;
			String message = "Server Error." ; 
			this.sendError(errorCode, message);
		}

		return representation;

	}

	/**
	 * Used to invalidate a bad request.
	 * 
	 * @param request
	 *            received from API consumer
	 * @return false if bad request
	 */
	private boolean isValid(ProductRequest request) {
		boolean result = true;
		if (request.getName().equals(null) || request.getName().equals("")) {
			result = false;
		}
		if (request.getCompanyUserName().equals(null) || request.getCompanyUserName().equals("")) {
			result = false;
		}
		return result;
	}

	/**
	 * 
	 * @param errorCode
	 * @param message
	 */
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
