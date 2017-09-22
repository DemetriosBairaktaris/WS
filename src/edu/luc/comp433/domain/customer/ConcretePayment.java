package edu.luc.comp433.domain.customer;

import java.util.Date;

public class ConcretePayment implements Payment {

  private String cardNumber;
  private String cardName;
  private String cvv;
  private Date expiration;

  public ConcretePayment() {
  }

  @Override
  public String getCardName() {
    return cardName;
  }

  @Override
  public String getCardNumber() {
    return cardNumber;
  }

  @Override
  public String getCvv() {
    return cvv;
  }

  @Override
  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  @Override
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  @Override
  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  @Override
  public Date getExpiration() {
    return expiration;
  }
}
