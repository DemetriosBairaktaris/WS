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

  public Response login(LoginRequest request);

}
