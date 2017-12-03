package edu.luc.comp433.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;
import java.sql.SQLException;
import java.util.HashSet;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;
import edu.luc.comp433.service.representation.ReviewRepresentation;
import edu.luc.comp433.service.representation.ReviewRequest;
import edu.luc.comp433.service.workflow.SalesActivity;
import edu.luc.comp433.service.workflow.ConcreteSalesActivity;

@Path("/products")
public class ProductResource implements ProductService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private SalesActivity facade = (ConcreteSalesActivity) context.getBean("salesActivity");
  private int key = 123456789;

  @GET
  @Path("/{productName}")
  @Produces({ "application/luc.products+json", "application/luc.products+xml" })
  @Override
  public Set<ProductRepresentation> getProduct(@PathParam("productName") String productName, @QueryParam("key") int api)
      throws SQLException, Exception {
    if (!this.checkKey(api)) {
      return null;
    }
    System.out.println("Received GET request to search products using parameter \"" + productName + ".\"");
    List<ProductRepresentation> list = facade.searchProduct(productName);
    HashSet<ProductRepresentation> products = new HashSet<ProductRepresentation>(list);
    return products;
  }

  @GET
  @Path("/{productName}/reviews")
  @Override
  public Set<ReviewRepresentation> getProductReviews(@PathParam("productName") String productName,
      @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return null;
    }
    Set<ReviewRepresentation> representations = null;
    try {
      representations = new HashSet<>(facade.getReviews(productName));
      System.out.println("Retrieving reviews for product: " + productName + ".");
    } catch (Exception e) {
      System.out.println("Error thrown when retrieving reviews.");
      System.out.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
    return representations;
  }

  @POST
  @Produces({ "application/luc.products+json", "application/luc.products+xml" })
  @Consumes({ "application/luc.products+json", "application/luc.products+xml" })
  @Override
  public Response insertProduct(ProductRequest request, @QueryParam("key") int api) {
    if (request.getName().equals(null) || request.getName().isEmpty()) {
      if (!this.checkKey(api)) {
        return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
      }
      System.out.println("Bad request to create product received.");
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
      System.out.println("Product: " + name + " created.");
    } catch (Exception e) {
      System.out.println("Error thrown when creating a product.");
      System.out.println(e.getMessage());
      e.printStackTrace();
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().entity(representation).build();
  }

  @DELETE
  @Path("/{productName}")
  @Override
  public Response deleteProduct(@QueryParam("companyUserName") String companyUserName,
      @PathParam("productName") String productName, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    Response response;
    if (companyUserName.isEmpty()) {
      response = Response.status(Status.BAD_REQUEST).entity("Could not delete product.").build();
    } else {
      try {
        facade.getProducts().deleteProduct(companyUserName, productName);
        response = Response.status(Status.OK).build();
      } catch (Exception e) {
        e.printStackTrace();
        response = Response.status(Status.BAD_REQUEST).entity("Could not delete product.").build();
      }
    }

    return response;
  }

  @POST
  @Path("/{productName}/reviews")
  @Consumes({ "application/luc.products+xml", "application/luc.products+json" })
  @Override
  public Response insertReview(ReviewRequest request, @PathParam("productName") String productName,
      @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    Response response;

    String content = request.getReview();
    int rating = request.getRating();

    try {
      facade.insertReview(productName, content, rating);
      response = Response.status(Status.OK).build();
    } catch (Exception e) {
      e.printStackTrace();
      response = Response.status(Status.BAD_REQUEST).entity("Unable to add review.").build();
    }
    return response;
  }

  private boolean checkKey(int api) {
    if (this.key == api) {
      return true;
    } else {
      return false;
    }
  }
}
