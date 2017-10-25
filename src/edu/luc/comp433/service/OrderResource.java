package edu.luc.comp433.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;
import edu.luc.comp433.service.workflow.DomainFacade;

@Path("/orders/")
public class OrderResource implements OrderService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

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
      return Response.status(Status.BAD_REQUEST).entity("Invalid order request.").build();
    }
    int currentOrderId = 0;
    for (OrderRequest singleRequest : request) {
      try {
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
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to purchase item.").build();
      }
    }

    representation = facade.getOrderById(currentOrderId);
    return Response.ok().entity(representation).build();
  }

  // TODO: update to send back details
  @GET
  @Path("/{orderId}")
  @Produces({ "application/json", "application/xml" })
  @Override
  public Response getOrder(@PathParam("orderId") int orderId) {
    OrderRepresentation representation = facade.getOrderById(orderId);
    if (representation.getOrderId() == -1) {
      return Response.status(Status.BAD_REQUEST).entity("Could not find resource").build();
    }
    return Response.ok().entity(representation).build();
  }

  @DELETE
  @Path("/{orderId}")
  @Consumes({ "application/json", "application/xml" })
  @Override
  public Response deleteOrder(@PathParam("orderId") int orderId) {
    try {
      facade.cancelOrder(orderId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to cancel order.").build();
    }
    return Response.ok().build();
  }

  @GET
  @Path("/partner/{partnerUserName}")
  @Produces({ "application/json", "application/xml" })
  @Override
  public Set<OrderRepresentation> getOrdersFromPartner(@PathParam("partnerUserName") String partnerUserName) {
    return new HashSet<OrderRepresentation>(facade.getOrdersFromPartner(partnerUserName));
  }

  // TODO add this back when used
  // private boolean isValid(OrderRequest request) {
  // boolean result = true;
  // if (request == null) {
  // result = false;
  // } else if (request.getQuantity() < 0) {
  // result = false;
  // }
  // return result;
  // }

  private boolean isValid(Set<OrderRequest> requests) {
    boolean result = true;
    if (requests == null) {
      result = false;
    }
    for (OrderRequest request : requests) {
      if (request.getQuantity() < 1) {
        result = false;
        break;
      }
    }
    return result;
  }

}
