package edu.luc.comp433.service.handler;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import edu.luc.comp433.service.representation.CustomerRequest;

public class ConcreteServerHandler implements ServerHandler {

  public ConcreteServerHandler() {
  }

  @Override
  public boolean isValid(CustomerRequest request) {
    if (request.getUserName().isEmpty()) {
      return false;
    }
    if (request.getCardNumber().isEmpty()) {
      return false;
    }
    return true;
  }

  @Override
  public void sendError(String message, HttpServletResponse response) {
    String fullMessage = "Error: " + message;
    try {
      response.setStatus(HttpStatus.SC_BAD_REQUEST);
      response.getOutputStream().print(fullMessage);
      response.getOutputStream().flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendSuccess(String message, HttpServletResponse response) {
    String fullMessage = "Success: " + message;
    try {
      response.setStatus(HttpStatus.SC_OK);
      response.getOutputStream().print(fullMessage);
      response.getOutputStream().flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
