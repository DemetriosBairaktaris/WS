package edu.luc.comp433.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.CustomerRequest;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;
import edu.luc.comp433.service.representation.OrderRequestCollection;
import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.representation.ProductRequest;

/**
 * Test client for running through the required methods.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public class TestUserClient {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private WebClient web;
  private String companyUserName;
  private String uri;
  private String header;
  private String response;
  private int orderId;

  public TestUserClient() {
    web = WebClient.create("http://localhost:8081");
    companyUserName = "management@partners.com";
  }

  public static void main(String args[]) {

    System.out.println("Starting user client test...");
    TestUserClient client = new TestUserClient();
    // Comment the functions out if you don't want to test it.

    // PARTNER POST TEST
    client.partnerPost();
    // PRODUCT POST TEST
    client.productPost();
    // CUSTOMER CREATE TEST
    client.createCustomer();
    // PRODUCT SEARCH TEST
    client.searchProduct();
    // ORDER PRODUCT TEST
    client.orderProduct();
    // ORDER WORKFLOW TEST
    client.orderWorkflow();
    // ORDER PUSH TO PARTNER TEST
    client.pushToPartner();
    // ORDER CANCEL TEST
    client.cancelOrder();
    // CUSTOMER DELETE TEST
    client.deleteCustomer();
    // PARTNER DELETE TEST
    client.deletePartner();

    System.out.println("Client test complete. Closing application.");
    System.exit(0);

  }

  public void partnerPost() {
    // PARTNER POST TEST
    System.out.println("Starting create partner test...");
    web = web.accept("application/xml").type("application/xml").path("/partners");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "partner", "POST");
    PartnerRequest partnerRequest = (PartnerRequest) context.getBean("partnerRequest");
    partnerRequest.setUserName(companyUserName);
    partnerRequest.setName("Test");
    partnerRequest.setAddress("123 Testing Avenue");
    partnerRequest.setPhone("3125555555");
    response = "";
    try {
      response = web.post(partnerRequest, String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Create partner test complete.");
  }

  public void productPost() {
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
    productRequest.setStock(30);
    try {
      response = web.post(productRequest, String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Create product test complete.");
  }

  public void createCustomer() {
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
  }

  public void searchProduct() {
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
  }

  public void orderProduct() {
    System.out.println("Starting order product test...");
    web.reset();
    web = web.accept("application/xml").type("application/xml").path("/orders");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "POST");
    OrderRequest orderRequest = (OrderRequest) context.getBean("orderRequest");
    Set<OrderRequest> requests = new HashSet<>();
    orderRequest.setProductName("Test Laptop");
    orderRequest.setPartner(companyUserName);
    orderRequest.setQuantity(1);
    orderRequest.setCustomer("testuser@tester.com");
    requests.add(orderRequest);
    OrderRequestCollection requestsCollection = new OrderRequestCollection(); // added
    requestsCollection.setRequests(requests);
    OrderRepresentation orderResponse = (OrderRepresentation) context.getBean("orderRepresentation");
    try {
      orderResponse = web.post(requestsCollection, OrderRepresentation.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    orderId = orderResponse.getOrderId();
    System.out.println("Server response: OrderID: " + orderId);
    System.out.println("Order product test complete.");
  }

  public void orderWorkflow() {
    System.out.println("Beginning order workflow tests...");
    getOrderStatus(web, uri, header, response, orderId);

    web.reset();
    web = web.path("/orders/" + orderId + "/status");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "PUT");
    try {
      response = web.put("").toString();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    getOrderStatus(web, uri, header, response, orderId);

    web.reset();
    web = web.path("/orders/" + orderId + "/shipment");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "PUT");
    try {
      response = web.put("").toString();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    getOrderStatus(web, uri, header, response, orderId);
    System.out.println("Order workflow test complete.");

  }

  public void pushToPartner() {
    System.out.println("Push orders to partners test starting...");
    web.reset();
    web = web.accept("application/xml").path("/partners/management@partners.com/orders");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "partner", "GET");
    try {
      response = web.get(String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Server response: " + response);
    System.out.println("Push orders to partners test complete.");
  }

  public void cancelOrder() {
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
  }

  public void deleteCustomer() {
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
  }

  public void deletePartner() {
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
  }

  public void printDetails(String uri, String header, String area, String method) {
    System.out.println(method + " URI for " + area + ": " + uri);
    System.out.println(method + " header for " + area + ": " + header);
  }

  public void getOrderStatus(WebClient web, String uri, String header, String response, int orderId) {
    web.reset();
    web = web.path("/orders/" + orderId + "/status");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "order", "GET");
    try {
      response = web.get(String.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
