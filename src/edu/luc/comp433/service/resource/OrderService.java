package edu.luc.comp433.service.resource;

import java.sql.SQLException;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.service.representation.OrderRequestCollection;

/**
 * Web service for orders.
 * 
 * @author Demetrios and Thaddeus
 *
 */
@WebService
public interface OrderService {

  /**
   * Retrieves an order.
   * 
   * @param orderId
   *          integer
   * @return HTTP Response
   */
  public Response getOrder(int orderId);

  /**
   * Deletes an order.
   * 
   * @param orderId
   *          integer
   * @return HTTP Response
   */
  public Response deleteOrder(int orderId);

  /**
   * Creates and inserts an order to the database.
   * 
   * @param request
   *          Set of order requests.
   * @return HTTP Response
   * @throws SQLException
   *           thrown by DB
   */
  public Response insertOrder(OrderRequestCollection requests) throws SQLException;

  /**
   * Sets an order to fulfilled status.
   * 
   * @param orderId
   *          integer
   * @return HTTP Response
   */
  public Response fulfillOrder(int orderId);

  /**
   * Checks the status of an order.
   * 
   * @param orderId
   *          integer
   * @return HTTP Response
   */
  public Response checkStatus(int orderId);

  /**
   * Sets the status of an order to shipped.
   * 
   * @param orderId
   *          integer
   * @return HTTP Response
   */
  public Response shipOrder(int orderId);



}
