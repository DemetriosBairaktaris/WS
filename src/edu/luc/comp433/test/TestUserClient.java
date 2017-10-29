package edu.luc.comp433.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.PartnerRequest;
import edu.luc.comp433.service.representation.ProductRequest;

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
    String response = web.post(partnerRequest, String.class);
    System.out.println("Server response: " + response);
    System.out.println("Create partner test complete.");

    // PRODUCT POST TEST
    System.out.println("Starting create product test...");
    web.reset();
    web = web.accept("application/xml").type("application/xml").path("/products/");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "product", "POST");
    ProductRequest productRequest = (ProductRequest) context.getBean("productRequest");
    productRequest.setCompanyUserName(companyUserName);
    productRequest.setName("Test Laptop");
    productRequest.setDesc("Strictly for testing.");
    productRequest.setCost(99);
    productRequest.setStock(1);
    response = web.post(productRequest, String.class);
    System.out.println("Server response: " + response);
    System.out.println("Create product test complete.");

    // PARTNER DELETE TEST
    System.out.println("Starting delete partner test...");
    web.reset();
    web = web.path("/partners/management@partners.com");
    uri = web.getCurrentURI().toString();
    header = web.getHeaders().toString();
    printDetails(uri, header, "partner", "DELETE");
    web.delete();
    System.out.println("Delete partner test complete.");

    System.out.println("Client test complete. Closing application.");
    System.exit(0);
  }

  private static void printDetails(String uri, String header, String area, String method) {
    System.out.println(method + " URI for " + area + ": " + uri);
    System.out.println(method + " header for " + area + ": " + header);
  }

}
