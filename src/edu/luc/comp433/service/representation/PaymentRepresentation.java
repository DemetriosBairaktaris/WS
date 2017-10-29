package edu.luc.comp433.service.representation;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Payment representation for XML and JSON.
 * 
 * @author Thaddeus and Demetrios
 *
 */
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PaymentRepresentation {

  private String cardNumber;
  private String cardName;
  private String cvv;
  private Date expiration;

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public Date getExpiration() {
    return expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

}
