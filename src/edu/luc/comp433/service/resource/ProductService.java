package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;
import edu.luc.comp433.service.representation.ReviewRepresentation;
import edu.luc.comp433.service.representation.ReviewRequest;

/**
 * Web service for products.
 * 
 * @author Demetrios and Thaddeus
 *
 */
@WebService
public interface ProductService {

  /**
   * Retrieves a product from the database for a client.
   * 
   * @param productName
   *          String
   * @param api
   *          integer key
   * @return Set of products
   * @throws SQLException
   *           thrown if database creates an error
   * @throws Exception
   *           thrown if issues
   */
  public Set<ProductRepresentation> getProduct(String productName, int api) throws SQLException, Exception;

  /**
   * Inserts a product from the client.
   * 
   * @param request
   *          Request from client
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response insertProduct(ProductRequest request, int api);

  /**
   * Gets reviews for a specified product.
   * 
   * @param productName
   *          String
   * @param api
   *          integer key
   * @return Set of review representations
   */
  public Set<ReviewRepresentation> getProductReviews(String productName, int api);

  /**
   * Inserts a review.
   * 
   * @param request
   *          ReviewRequest
   * @param productName
   *          String
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response insertReview(ReviewRequest request, String productName, int api);

  /**
   * Deletes a product.
   * 
   * @param companyUserName
   *          String
   * @param productName
   *          String
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response deleteProduct(String companyUserName, String productName, int api);

}
