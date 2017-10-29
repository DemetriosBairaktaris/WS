package edu.luc.comp433.service.resource;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;

/**
 * Web service for partners.
 * 
 * @author Demetrios and Thaddeus
 *
 */
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

  public Set<OrderRepresentation> getOrdersFromPartner(String partnerUserName);

  /**
   * Updates a partner's company name.
   * 
   * @param partnerName
   *          String
   * @param companyName
   *          String
   * @return HTTP response
   */
  public Response updateName(String partnerName, String companyName);

  /**
   * Updates a partners address.
   * 
   * @param partnerName
   *          String
   * @param address
   *          String
   * @return HTTP response
   */
  public Response updateAddress(String partnerName, String address);

  /**
   * Updates a partner's phone number.
   * 
   * @param partnerName
   *          String
   * @param phone
   *          String
   * @return HTTP response
   */
  public Response updatePhone(String partnerName, String phone);

}
