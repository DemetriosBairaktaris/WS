package edu.luc.comp433.service;

import java.sql.SQLException;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;

@WebService
public interface ProductService {

  /**
   * Retrieves a product from the database for a client.
   * 
   * @param productName
   *          String
   * @return Set of products
   * @throws SQLException
   *           thrown if database creates an error
   * @throws Exception
   *           thrown if issues
   */
  public Set<ProductRepresentation> getProduct(String productName) throws SQLException, Exception;

  /**
   * Inserts a product from the client.
   * 
   * @param request
   *          Request from client.
   * @return HTTP Response
   */
  public Response insertProduct(ProductRequest request);

}
