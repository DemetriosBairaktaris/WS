package edu.luc.comp433.service;

import javax.jws.WebService;

import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.PartnerRequest;

@WebService
public interface PartnerService {

  PartnerRepresentation insertPartner(PartnerRequest request);

}
