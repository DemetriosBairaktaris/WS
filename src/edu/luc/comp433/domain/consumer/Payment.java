package edu.luc.comp433.domain.consumer;

public interface Payment {
    public void setCardNumber(String cardNumber);
    public String getCardNumber();
    public void setCardName(String cardName);
    public String getCardName();
    public void setCVV(String CVV);
    public String getCVV();
}
