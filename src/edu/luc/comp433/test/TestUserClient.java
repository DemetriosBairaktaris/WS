package edu.luc.comp433.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.representation.ProductRequest;

/**
 * Test client for running through the required methods.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public class TestUserClient {

  private static ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");

  public TestUserClient() {
  }

  public static void main(String args[]) {

    System.out.println("Starting user client test...");

    WebClient web = WebClient.create("http://localhost:8081");
    String companyUserName = "management@partners.com";

    // PARTNER POST TEST
    System.out.println("Starting create partner test...");
    web = web.accept("application/xml").type("application/xml").path("/partners");
    String uri = web.getCurrentURI().toString();
    String header = web.getHeaders().toString();
    printDetails(uri, header, "partner", "POST");
    PartnerRequest partnerRequest = (PartnerRequest) context.getBean("partnerRequest");
    partnerRequest.setUserName(companyUserName);
    partnerRequest.setName("Test");
    partnerRequest.setAddress("123 Testing Avenue");
    partnerRequest.setPhone("3125555555");
    String response = "";
    try {
      response = web.post(partnerRequest, String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Create partner test complete.");

    // PRODUCT POST TEST
    System.out.println("Starting create product test...");
    web.reset();
    web = web.accept("application/xml").type("application/xml").path("/products");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "product", "POST");
    ProductRequest productRequest = (ProductRequest) context.getBean("productRequest");
    productRequest.setCompanyUserName(companyUserName);
    productRequest.setName("Test Laptop");
    productRequest.setDesc("Strictly for testing.");
    productRequest.setCost(99);
    productRequest.setStock(1);
    try {
      response = web.post(productRequest, String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Create product test complete.");

    // CUSTOMER CREATE TEST
    System.out.println("Starting create customer test...");
    web.reset();
    web = web.accept("application/xml").type("application/xml").path("/customers");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "customer", "POST");
    CustomerRequest customerRequest = (CustomerRequest) context.getBean("customerRequest");
    customerRequest.setUserName("testuser@tester.com");
    customerRequest.setFirstName("Test");
    customerRequest.setLastName("User");
    customerRequest.setAddress("987 Fake Street");
    customerRequest.setPhone("7735555555");
    customerRequest.setCardName("Test User");
    customerRequest.setCardNumber("1234432112344321");
    customerRequest.setCvv("999");
    customerRequest.setExpiration("10-25");
    try {
      response = web.post(customerRequest, String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Create customer test complete.");

    // PRODUCT SEARCH TEST
    System.out.println("Starting product search test...");
    web.reset();
    web = web.path("/products/Test Laptop");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "product", "GET");
    try {
      response = web.get(String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Response to query: " + response);
    System.out.println("Product search test complete.");

    // ORDER PRODUCT TEST
    System.out.println("Starting order product test...");
    web.reset();
    web = web.accept("application/xml").type("application/xml").path("/orders");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "POST");
    OrderRequest orderRequest = (OrderRequest) context.getBean("orderRequest");
    orderRequest.setProductName("Test Laptop");
    orderRequest.setPartner(companyUserName);
    orderRequest.setQuantity(1);
    orderRequest.setCustomer("testuser@tester.com");
    OrderRepresentation orderResponse = (OrderRepresentation) context.getBean("orderRepresentation");
    try {
      orderResponse = web.post(orderRequest, OrderRepresentation.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    int orderId = orderResponse.getOrderId();
    System.out.println("Server response: OrderID: " + orderId);
    System.out.println("Order product test complete.");

    // ORDER WORKFLOW TEST

    // ORDER CANCEL TEST
    System.out.println("Starting order cancel test...");
    web.reset();
    web = web.path("/orders/" + orderId);
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "DElETE");
    try {
      web.delete();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Order delete test complete.");

    // CUSTOMER DELETE TEST
    System.out.println("Starting delete customer test...");
    web.reset();
    web = web.path("/customers/testuser@tester.com");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "customer", "DELETE");
    try {
      web.delete();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Delete customer test complete.");

    // PARTNER DELETE TEST
    System.out.println("Starting delete partner test...");
    web.reset();
    web = web.path("/partners/management@partners.com");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "partner", "DELETE");
    try {
      web.delete();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Delete partner test complete.");

    System.out.println("Client test complete. Closing application.");
    System.exit(0);
  }

  private static void printDetails(String uri, String header, String area, String method) {
    System.out.println(method + " URI for " + area + ": " + uri);
    System.out.println(method + " header for " + area + ": " + header);
  }

}
