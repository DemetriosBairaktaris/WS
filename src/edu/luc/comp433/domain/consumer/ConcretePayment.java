package edu.luc.comp433.domain.consumer;

public class ConcretePayment implements Payment {

    private String cardNumber;
    private String cardName;
    private double CVV;
    
    public ConcretePayment() {}
    
    @Override
    public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
    }
    
    @Override
    public String getCardNumber() {
	return cardNumber;
    }
    
    @Override
    public void setCardName(String cardName) {
	this.cardName = cardName;
    }
    
    @Override
    public String getCardName() {
	return cardName;
    }
    
    @Override
    public void setCVV(double CVV) {
	this.CVV = CVV;
    }
    
    @Override
    public double getCVV() {
	return CVV;
    }
}
