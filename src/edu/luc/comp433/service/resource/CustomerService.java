package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.text.ParseException;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.CustomerRequest;

/**
 * Interface for the customer web service.
 * 
 * @author Thaddeus and Demetrios
 *
 */
@WebService
public interface CustomerService {

  /**
   * Inserts a customer to the database from an API call.
   * 
   * @param request
   *          CustomerRequest
   * @return HTTP response
   * @throws ParseException
   *           thrown if parse errors
   */
  public Response insertCustomer(CustomerRequest request, int api) throws ParseException;

  /**
   * Retrieves a customer's status.
   * 
   * @param userName
   *          String
   * @return HTTP Response
   * @throws SQLException
   *           thrown if database not working
   */
  public Response getCustomerStatus(String userName, int api) throws SQLException;

  /**
   * Returns a customer object representation.
   * 
   * @param userName
   *          String
   * @return HTTP Response
   * @throws SQLException
   *           thrown if database error
   */
  public Response getCustomer(String userName, int api) throws SQLException;

  /**
   * Deletes a customer.
   * 
   * @param userName
   *          String
   * @return HTTP Response
   * @throws SQLException
   *           thrown if database error
   */
  public Response deleteCustomer(String userName, int api) throws SQLException;

  /**
   * Updates a customer.
   * 
   * @param request
   *          customer request
   * @return HTTP Response
   * @throws ParseException
   *           thrown if can't parse the request.
   */
  public Response updateCustomer(CustomerRequest request, int api) throws ParseException;

}
