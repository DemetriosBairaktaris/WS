package edu.luc.comp433.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.service.representation.PartnerRequest;

public class TestUserClient {

  private static ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");

  public TestUserClient() {
  }

  public static void main(String args[]) {

    System.out.println("Starting user client test...");

    WebClient web = WebClient.create("http://localhost:8081");

    // PARTNER POST TEST
    System.out.println("Starting create partner test...");
    web = web.accept("application/xml").type("application/xml").path("/partners");
    String requestURI = web.getCurrentURI().toString();
    System.out.println("POST URI for partner request: " + requestURI);
    String requestHeader = web.getHeaders().toString();
    System.out.println("POST Header for partner requst: " + requestHeader);
    PartnerRequest partnerRequest = (PartnerRequest) context.getBean("partnerRequest");
    partnerRequest.setUserName("management@partners.com");
    partnerRequest.setName("Test");
    partnerRequest.setAddress("123 Testing Avenue");
    partnerRequest.setPhone("3125555555");
    String response = web.post(partnerRequest, String.class);
    System.out.println("Server response: " + response);
    System.out.println("Create partner test complete.");

    // PARTNER DELETE TEST
    System.out.println("Starting delete partner test...");
    web.reset();
    web = web.path("/partners/management@partners.com");
    String deleteURI = web.getCurrentURI().toString();
    System.out.println("DELETE URI for partner: " + deleteURI);
    String deleteHeader = web.getHeaders().toString();
    System.out.println("DELETE header for partner: " + deleteHeader);
    web.delete();
    System.out.println("Delete partner test complete.");

    System.out.println("Client test complete. Closing application.");
    System.exit(0);
  }

}
