package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Order detail representation for XML and JSON.
 * 
 * @author Demetrios and Thaddeus
 *
 */
@XmlRootElement(name = "orderDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderDetailRepresentation extends AbstractRepresentation {

  private long quantity;
  private String status;
  private String company;

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

}
