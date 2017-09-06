package edu.luc.comp433.domain;

public class Payment {

    private String cardNumber;
    private String cardName;
    private double CVV;
    private String billingAddress;
    
    public Payment() {}
    
    public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
    }
    
    public String getCardNumber() {
	return cardNumber;
    }
    
    public void setCardName(String cardName) {
	this.cardName = cardName;
    }
    
    public String getCardName() {
	return cardName;
    }
    
    public void setCVV(double CVV) {
	this.CVV = CVV;
    }
    
    public double getCVV() {
	return CVV;
    }
    
    public void setBillingAddress(String billingAddress) {
	this.billingAddress = billingAddress;
    }
    
    public String getBillingAddress() {
	return billingAddress;
    }
}
