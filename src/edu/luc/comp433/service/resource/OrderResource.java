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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;
import edu.luc.comp433.service.workflow.ConcreteSalesActivity;
import edu.luc.comp433.service.workflow.SalesActivity;

@Path("/orders/")
public class OrderResource implements OrderService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private SalesActivity facade = (ConcreteSalesActivity) context.getBean("salesActivity");

  @POST
  @Consumes({ "application/json", "application/xml" })
  @Produces({ "application/json", "application/xml" })
  @Override
  public Response insertOrder(Set<OrderRequest> request) throws SQLException {
    OrderRepresentation representation = (OrderRepresentation) context.getBean("orderRepresentation");
    /**
     * Steps: 3: get the representation and return
     */
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
    return Response.ok().entity(representation).build();
  }

  @GET
  @Path("/{orderId}")
  @Produces({ "application/json", "application/xml" })
  @Override
  public Response getOrder(@PathParam("orderId") int orderId) {
    OrderRepresentation representation = facade.getOrderById(orderId);
    if (representation.getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found.");
      return Response.status(Status.BAD_REQUEST).entity("Could not find resource").build();
    }
    System.out.println("Order: " + orderId + " found.");
    return Response.ok().entity(representation).build();
  }

  @GET
  @Path("/{orderId}/status")
  @Override
  public Response checkStatus(@PathParam("orderId") int orderId) {
    if (facade.getOrderById(orderId).getOrderId() == -1) {
      System.out.println("Order: " + orderId + " not found. Cannot check status.");
      return Response.status(Status.BAD_REQUEST).entity("Order not found.").build();
    } else {
      System.out.println("Order: " + orderId + " found. Returning status...");
      return Response.ok().entity(facade.getOrderById(orderId).getStatus()).build();
    }
  }

  @PUT
  @Path("/{orderId}/status")
  @Override
  public Response fulfillOrder(@PathParam("orderId") int orderId) {
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
  public Response shipOrder(@PathParam("orderId") int orderId) {
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
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response deleteOrder(@PathParam("orderId") int orderId) {
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

}
