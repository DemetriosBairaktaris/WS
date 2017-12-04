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
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response insertPartner(PartnerRequest request, int api);

  /**
   * Deletes a partner profile.
   * 
   * @param partnerName
   *          String
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response deletePartner(String partnerName, int api);

  /**
   * Retrieves orders for a specific partner.
   * 
   * @param partnerUserName
   *          String
   * @param api
   *          integer key
   * @return set of orders
   */
  public Set<OrderRepresentation> getOrdersFromPartner(String partnerUserName, int api);

  /**
   * Updates a partner's company name.
   * 
   * @param partnerName
   *          String
   * @param companyName
   *          String
   * @param api
   *          integer key
   * @return HTTP response
   */
  public Response updateName(String partnerName, String companyName, int api);

  /**
   * Updates a partners address.
   * 
   * @param partnerName
   *          String
   * @param address
   *          String
   * @param api
   *          integer key
   * @return HTTP response
   */
  public Response updateAddress(String partnerName, String address, int api);

  /**
   * Updates a partner's phone number.
   * 
   * @param partnerName
   *          String
   * @param phone
   *          String
   * @param api
   *          integer key
   * @return HTTP response
   */
  public Response updatePhone(String partnerName, String phone, int api);

}
