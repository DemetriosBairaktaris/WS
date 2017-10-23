package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {

  private String customer;
  
  public OrderRequest() {
	  this.customer = null ; 
  }
  public void setCustomer(String customer) {
	  this.customer = customer ;
  }
  
  public String getCustomer(){
	  return this.customer ; 
  }
}
