package edu.luc.comp433.service;

import java.sql.SQLException;
import java.util.Set;

import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;

public interface OrderService {

  public Response getOrder(int orderId);

  public Set<OrderRepresentation> getOrdersFromPartner(String partnerUserName);

  public Response deleteOrder(int orderId);

  public Response insertOrder(Set<OrderRequest> request) throws SQLException;

}
