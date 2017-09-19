package edu.luc.comp433.domain.customer;

public class ConcretePayment implements Payment {

    private String cardNumber;
    private String cardName;
    private String CVV;
    
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
    public void setCVV(String CVV) {
	this.CVV = CVV;
    }
    
    @Override
    public String getCVV() {
	return CVV;
    }
}
