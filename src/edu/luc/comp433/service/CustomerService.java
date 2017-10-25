package edu.luc.comp433.service;

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
   * @throws ParseException
   *           thrown if parse errors
   */
  public Response insertCustomer(CustomerRequest request) throws ParseException;

}
