package edu.luc.comp433.domain.order;

public interface Payment {
    public void setCardNumber(String cardNumber);
    public String getCardNumber();
    public void setCardName(String cardName);
    public String getCardName();
    public void setCVV(double CVV);
    public double getCVV();
    public void setBillingAddress(String billingAddress);
    public String getBillingAddress();
}
