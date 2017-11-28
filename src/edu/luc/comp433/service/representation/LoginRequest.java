package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representation for a login request.
 * 
 * @author Thaddeus and Demetrios
 *
 */
@XmlRootElement(name = "Login")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class LoginRequest {

  private String userName;
  private String password;

  public LoginRequest() {
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
