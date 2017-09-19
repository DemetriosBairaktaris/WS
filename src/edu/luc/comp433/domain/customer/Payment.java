package edu.luc.comp433.domain.customer;

public interface Payment {
  public String getCardName();

  public String getCardNumber();

  public String getCvv();

  public void setCardName(String cardName);

  public void setCardNumber(String cardNumber);

  public void setCvv(String cvv);
}
