package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Partner representation to help parse XML and JSON requests.
 * 
 * @author Thaddues and Demetrios
 *
 */
@XmlRootElement(name = "Partner")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerRequest {

  private String userName;
  private String name;
  private String address;
  private String phone;
  private String password ;

  public PartnerRequest() {
    userName = "";
    name = "";
    address = "";
    phone = "";
    password = "" ; 
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
  
  public void setPassword(String password) {
    this.password = password ; 
  }
  
  public String getPassword() {
    return this.password ; 
  }

}
