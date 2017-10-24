package edu.luc.comp433.service.handler;

import javax.servlet.http.HttpServletResponse;

import edu.luc.comp433.service.representation.CustomerRequest;

public interface ServerHandler {

  public boolean isValid(CustomerRequest request);
  
  public void sendError(String message, HttpServletResponse response);
  
  public void sendSuccess(String message, HttpServletResponse response);
  
}
