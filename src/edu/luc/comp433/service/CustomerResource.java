package edu.luc.comp433.service;

import javax.ws.rs.Path;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.workflow.ConcreteDomainFacade;
import edu.luc.comp433.service.workflow.DomainFacade;

@Path("/customer/")
public class CustomerResource implements CustomerService {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DomainFacade facade = (ConcreteDomainFacade) context.getBean("domain");

  @Context
  private HttpServletResponse response;
  private int errorCode;

  public CustomerResource() {
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  @Override
  public void insertCustomer(CustomerRequest request) throws ParseException {
    if (!isValid(request)) {
      errorCode = 400;
      String message = "unable to insert customer, user name or card invalid.";
      this.sendError(errorCode, message);
    }

    String userName = request.getUserName();
    String firstName = request.getFirstName();
    String lastName = request.getLastName();
    String address = request.getAddress();
    String phone = request.getPhone();
    String cardName = request.getCardName();
    String cardNumber = request.getCardNumber();
    String cvv = request.getCvv();
    String expiration = request.getExpiration();
    SimpleDateFormat format = new SimpleDateFormat("MM-yy");
    Date date = format.parse(expiration);

    try {
      facade.getCustomers().createCustomer(userName, firstName, lastName, address, phone, cardName, cardNumber, cvv,
          date);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      errorCode = 500;
      String message = "Server Error.";
      this.sendError(errorCode, message);
    }
    String message = "Customer created.";
    this.sendSuccess(message);
  }

  /**
   * Used to invalidate a bad request.
   * 
   * @param request
   *          received from API consumer
   * @return false if bad request
   */
  private boolean isValid(CustomerRequest request) {
    boolean result = true;
    if (request.getUserName().equals(null) || request.getUserName().equals("")) {
      result = false;
    }
    if (request.getCardNumber().equals(null) || request.getCardNumber().equals("")) {
      result = false;
    }
    return result;
  }

  /**
   * 
   * @param errorCode
   * @param message
   */
  private void sendError(int errorCode, String message) {
    String fullMessage = "Error: " + errorCode + " " + message;
    try {
      response.getOutputStream().print(fullMessage);
      response.getOutputStream().flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void sendSuccess(String message) {
    String fullMessage = "Success: " + message;
    try {
      response.getOutputStream().print(fullMessage);
      response.getOutputStream().flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
