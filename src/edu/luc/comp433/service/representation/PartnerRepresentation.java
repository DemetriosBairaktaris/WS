package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Partner representation for XML and JSON.
 * 
 * @author Thaddues and Demetrios
 *
 */
@XmlRootElement(name = "Partner")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerRepresentation extends AbstractRepresentation {

  private String userName;
  private String name;
  private String address;
  private String phone;

  public PartnerRepresentation() {
    userName = "";
    name = "";
    address = "";
    phone = "";
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
