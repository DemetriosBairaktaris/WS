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
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response getOrder(int orderId, int api);

  /**
   * Deletes an order.
   * 
   * @param orderId
   *          integer
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response deleteOrder(int orderId, int api);

  /**
   * Creates and inserts an order to the database.
   * 
   * @param requests
   *          Order Request Collection
   * @param api
   *          integer key
   * @return HTTP Response
   * @throws SQLException
   *           thrown by DB
   */
  public Response insertOrder(OrderRequestCollection requests, int api) throws SQLException;

  /**
   * Sets an order to fulfilled status.
   * 
   * @param orderId
   *          integer
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response fulfillOrder(int orderId, int api);

  /**
   * Checks the status of an order.
   * 
   * @param orderId
   *          integer
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response checkStatus(int orderId, int api);

  /**
   * Sets the status of an order to shipped.
   * 
   * @param orderId
   *          integer
   * @param api
   *          integer key
   * @return HTTP Response
   */
  public Response shipOrder(int orderId, int api);

}
