package edu.luc.comp433.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/product/")
public class ProductResource implements ProductService {

  @GET
  @Produces({ "text/plain" })
  @Path("/test")
  public String testService() {
    System.out.println("Testing service");
    return "Test successful!";
  }
}
