package edu.luc.comp433.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;
import java.sql.SQLException;
import java.util.HashSet;

import edu.luc.comp433.domain.product.Product;
import edu.luc.comp433.domain.product.Review;
import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;
import edu.luc.comp433.service.representation.ReviewRepresentation;
import edu.luc.comp433.service.workflow.DomainFacade;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;

@Path("/products/")
public class ProductResource implements ProductService {

	private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
	private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

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

	@GET
	@Path("/{productName}/reviews")

	public Set<ReviewRepresentation> getProductReviews(@PathParam("productName") String productName) {
		Set<ReviewRepresentation> representations = new HashSet<>();
		try {
			List<Product> products = facade.getProducts().getProducts(productName);
			for (Product p : products) {
				for (Review review : p.getReviews()) {
					ReviewRepresentation r = new ReviewRepresentation();
					r.setRating(review.getRating());
					r.setReview(review.getReview());
					representations.add(r);
				}

			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return representations;
	}

	@POST
	@Produces({ "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@Override
	public Response insertProduct(ProductRequest request) {
		if (request.getName().equals(null) || request.getName().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("Unable to insert product.").build();
		}
		String name = request.getName();
		String companyUserName = request.getCompanyUserName();
		String desc = request.getDesc();
		long stock = request.getStock();
		float cost = (float) request.getCost();
		ProductRepresentation representation = (ProductRepresentation) context.getBean("productRepresentation");
		try {
			facade.getProducts().addProduct(name, desc, cost, stock, companyUserName);
			representation = facade.getProductFromPartner(name, companyUserName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.ok().entity(representation).build();
	}
}
