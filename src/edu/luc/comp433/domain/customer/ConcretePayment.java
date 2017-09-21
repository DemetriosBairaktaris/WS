package edu.luc.comp433.domain.customer;

public class ConcretePayment implements Payment {

  //TODO add expiration date attribute and methods
  private String cardNumber;
  private String cardName;
  private String cvv;

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
}
