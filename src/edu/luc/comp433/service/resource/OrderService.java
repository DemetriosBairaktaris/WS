package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.util.Set;

import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.OrderRequest;

public interface OrderService {

  public Response getOrder(int orderId);

  public Response deleteOrder(int orderId);

  public Response insertOrder(Set<OrderRequest> request) throws SQLException;

  public Response fulfillOrder(int orderId);

  public Response checkStatus(int orderId);

  public Response shipOrder(int orderId);

}
