package edu.luc.comp433.service.resources;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.PartnerRequest;

@WebService
public interface PartnerService {

  /**
   * Inserts a partner from the client.
   * 
   * @param request
   *          PartnerRequest
   * @return HTTP Response
   */
  public Response insertPartner(PartnerRequest request);

  /**
   * Deletes a partner profile.
   * 
   * @param partnerName
   *          String
   * @return HTTP Response
   */
  public Response deletePartner(String partnerName);

}
