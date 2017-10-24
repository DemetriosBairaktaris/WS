package edu.luc.comp433.service;

import java.text.ParseException;

import javax.jws.WebService;

import edu.luc.comp433.service.representation.CustomerRequest;

@WebService
public interface CustomerService {

  public void insertCustomer(CustomerRequest request) throws ParseException;

}
