package edu.luc.comp433.service.representation;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Wrapper for order requests.
 * 
 * @author Demetrios and Thaddeus
 *
 */
@XmlRootElement(name = "Orders")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequestCollection {

  private Set<OrderRequest> requests;

  public OrderRequestCollection() {
    requests = new HashSet<>();
  }

  public void addRequest(OrderRequest request) {
    this.requests.add(request);
  }

  public Set<OrderRequest> getRequests() {
    return requests;
  }

  public void setRequests(Set<OrderRequest> requests) {
    this.requests = requests;
  }
}
