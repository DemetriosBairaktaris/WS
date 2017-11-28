package edu.luc.comp433.service.resource;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.LoginRequest;

/**
 * Login web service.
 * 
 * @author Thaddeus and Demetrios
 *
 */
@WebService
public interface LoginService {

  /**
   * Logs in a user and determines if they are partner or customer.
   * 
   * @param request
   *          request
   * @return HTTP Response
   */
  public Response login(LoginRequest request);

}
