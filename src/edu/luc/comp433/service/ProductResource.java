package edu.luc.comp433.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.*;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;

@Path("/product/")
public class ProductResource implements ProductService {

	@GET
	@Path("/{productName}")
	@Produces({ "text/plain", "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@Override
	public Set<ProductRepresentation> getProduct(@PathParam("productName") String productName) {
		// TODO: make return type to Set<ProductReprestation>
		// call to activity/workflow and get all products

		//
		return new HashSet<ProductRepresentation>();
	}

	@POST
	@Produces({ "text/plain", "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@Override
	public ProductRepresentation insertProduct(ProductRequest request) {
		if (!hasIntegrity(request)) {
			// bad request level 400
			// no reason to waste a DB call if the product aint valid.
			return null;
		}
		String name = request.getName();
		String companyUserName = request.getCompanyUserName();
		String desc = request.getDesc();
		int stock = (int) request.getStock();
		float cost = (float) request.getCost();

		// TODO call to workflow/activity to insert a product
		// return newly created representation
		return new ProductRepresentation();

	}

	private boolean hasIntegrity(ProductRequest request) {
		boolean result = true;
		if (request.getName().equals(null) || request.getName().equals("")) {
			result = false;
		}
		if (request.getCompanyUserName().equals(null) || request.getCompanyUserName().equals("")) {
			result = false;
		}
		return result;
	}
}
