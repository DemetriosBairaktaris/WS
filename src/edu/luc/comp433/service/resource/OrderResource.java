package edu.luc.comp433.service.resource;

import java.sql.SQLException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;
import edu.luc.comp433.service.representation.OrderRequestCollection;
import edu.luc.comp433.service.representation.ProtocolLink;
import edu.luc.comp433.service.workflow.ConcreteSalesActivity;
import edu.luc.comp433.service.workflow.SalesActivity;

@Path("/orders/")
public class OrderResource implements OrderService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private SalesActivity facade = (ConcreteSalesActivity) context.getBean("salesActivity");
  private int key = 123456789;

  @POST
  @Consumes({ "application/luc.orders+json", "application/luc.orders+xml" })
  @Produces({ "application/luc.orders+json", "application/luc.orders+xml" })
  @Override
  public Response insertOrder(OrderRequestCollection requests, @QueryParam("key") int api) throws SQLException {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }

    OrderRepresentation representation = (OrderRepresentation) context.getBean("orderRepresentation");

    Set<OrderRequest> request = requests.getRequests(); // added this // changed param from Set<OrderReq>
    if (!this.isValid(request)) {
      System.out.println("Bad order request. Cannot create order.");
      return Response.status(Status.BAD_REQUEST).entity("Invalid order request.").build();
    }
    int currentOrderId = 0;
    for (OrderRequest singleRequest : request) {
      try {
        System.out.println("Creating order...");
        // passing in zero for orderId first to guarantee new order creation, otherwise
        // it
        // updates an existing order
        // each subsequent order after the first should have currentOrderId set to a
        // non-zero value
        System.out.println("request == null?:");
        System.out.println(request == null);
        int orderId = facade.buyProduct(singleRequest.getCustomer(), singleRequest.getProductName(),
            singleRequest.getQuantity(), currentOrderId);
        if (orderId == -1) {
          throw new Exception();
        }
        currentOrderId = orderId;

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Exception thrown when creating order.");
        System.out.println(e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to purchase item.").build();
      }
    }

    System.out.println("Order: " + currentOrderId + " created.");
    representation = facade.getOrderById(currentOrderId);
    ProtocolLink link = (ProtocolLink) context.getBean("link");
    ProtocolLink link1 = (ProtocolLink) context.getBean("link");
    link.setAction("GET");
    link.setContentType("none");
    link.setRel("Check status");
    link.setUri("/orders/" + currentOrderId + "/status");
    link1.setAction("DELETE");
    link1.setContentType("none");
    link1.setRel("Cancel Order");
    link1.setUri("/orders/" + currentOrderId);
    representation.addLink(link);
    representation.addLink(link1);
    return Response.ok().entity(representation).build();
  }

  @GET
  @Path("/{orderId}")
  @Produces({ "application/luc.orders+json", "application/luc.orders+xml" })
  @Override
  public Response getOrder(@PathParam("orderId") int orderId, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    OrderRepresentation representation = facade.getOrderById(orderId);
    if (representation.getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found.");
      return Response.status(Status.BAD_REQUEST).entity("Could not find resource").build();
    }
    System.out.println("Order: " + orderId + " found.");
    ProtocolLink link = (ProtocolLink) context.getBean("link");
    ProtocolLink link1 = (ProtocolLink) context.getBean("link");
    ProtocolLink link2 = (ProtocolLink) context.getBean("link");
    link.setAction("PUT");
    link.setContentType("none");
    link.setRel("Fulfill order");
    link.setUri("/orders/" + orderId + "/fulfillment");
    link1.setAction("PUT");
    link1.setContentType("none");
    link1.setRel("Ship order");
    link1.setUri("/orders/" + orderId + "/shipment");
    link2.setAction("DELETE");
    link2.setContentType("none");
    link2.setRel("Cancel Order");
    link2.setUri("/orders/" + orderId);
    representation.addLink(link);
    representation.addLink(link1);
    representation.addLink(link2);
    return Response.ok().entity(representation).build();
  }

  @GET
  @Path("/{orderId}/status")
  @Override
  public Response checkStatus(@PathParam("orderId") int orderId, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    if (facade.getOrderById(orderId).getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found. Cannot check status.");
      return Response.status(Status.BAD_REQUEST).entity("Order not found.").build();
    } else {
      System.out.println("Order: " + orderId + " found. Returning status...");
      return Response.ok().entity(facade.getOrderById(orderId).getStatus()).build();
    }
  }

  @PUT
  @Path("/{orderId}/fulfillment")
  @Override
  public Response fulfillOrder(@PathParam("orderId") int orderId, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    if (facade.getOrderById(orderId).getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found. Cannot fulfill.");
      return Response.status(Status.BAD_REQUEST).entity("Order not found.").build();
    } else {
      try {
        facade.fulfillOrder(orderId);
        System.out.println("Order: " + orderId + " found. Order fulfilled.");
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Exception thrown when fulfilling order.");
        System.out.println(e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      } catch (Exception e) {
        System.out.println("Exception thrown when fulfilling order.");
        System.out.println(e.getMessage());
        e.printStackTrace();
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      }
    }
    return Response.ok().build();
  }

  @PUT
  @Path("/{orderId}/shipment")
  @Override
  public Response shipOrder(@PathParam("orderId") int orderId, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    if (facade.getOrderById(orderId).getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found. Cannot ship.");
      return Response.status(Status.BAD_REQUEST).entity("Order not found.").build();
    } else {
      try {
        facade.shipOrder(orderId);
        System.out.println("Order: " + orderId + " found. Order shipped.");
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Exception thrown when shipping order.");
        System.out.println(e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Exception thrown when shipping order.");
        System.out.println(e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      }
      return Response.ok().entity("Order shipped.").build();
    }
  }

  @DELETE
  @Path("/{orderId}")
  @Override
  public Response deleteOrder(@PathParam("orderId") int orderId, @QueryParam("key") int api) {
    if (!this.checkKey(api)) {
      return Response.status(Status.UNAUTHORIZED).entity("Incorrect API Key").build();
    }
    try {
      facade.cancelOrder(orderId);
      System.out.println("Order: " + orderId + " found. Order canceled.");
    } catch (Exception e) {
      System.out.println("Exception thrown when deleting order.");
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to cancel order.").build();
    }
    return Response.ok().build();
  }

  private boolean isValid(Set<OrderRequest> requests) {
    boolean result = true;
    if (requests == null) {
      System.out.println("Request null.");
      result = false;
    }
    for (OrderRequest request : requests) {
      if (request.getQuantity() < 1) {
        System.out.println(requests.size());
        System.out.println("Cannot have 0 quantity.");
        result = false;
        break;
      }
    }
    return result;
  }

  private boolean checkKey(int api) {
    if (this.key == api) {
      return true;
    } else {
      return false;
    }
  }

}
