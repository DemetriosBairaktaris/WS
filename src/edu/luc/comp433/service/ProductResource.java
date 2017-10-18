package edu.luc.comp433.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.*;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;
import edu.luc.comp433.service.workflow.DomainFacade;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;

@Path("/product/")
public class ProductResource implements ProductService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

  public ProductResource() {
  }

  @GET
  @Path("/{productName}")
  @Produces({ "application/json", "application/xml" })
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Set<ProductRepresentation> getProduct(@PathParam("productName") String productName)
      throws SQLException, Exception {
    HashSet<ProductRepresentation> hs = new HashSet<ProductRepresentation>();
    ProductRepresentation test = new ProductRepresentation();
    List<String> list = facade.searchProduct(productName);
    System.out.println("GET request to search products using parameter " + productName);
    for (int i = 0; i < list.size(); i += 4) {
      test.setName(list.get(i));
      test.setDesc(list.get(i + 1));
      test.setCost(list.get(i + 2));
      test.setStock(list.get(i + 3));
      hs.add(test);
    }
    return hs;
  }

  @POST
  @Produces({ "text/plain", "application/json", "application/xml" })
  @Consumes({ "application/json", "application/xml" })
  @Override
  public ProductRepresentation insertProduct(ProductRequest request) {
    if (!isValid(request)) {
      // bad request level 400
      // no reason to waste a DB call if the product aint valid.
      return null;
    }
    /*
     * String name = request.getName(); String companyUserName =
     * request.getCompanyUserName(); String desc = request.getDesc(); int stock =
     * (int) request.getStock(); float cost = (float) request.getCost();
     */

    // TODO call to workflow/activity to insert a product
    // return newly created representation
    return new ProductRepresentation();

  }

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
}
