package edu.luc.comp433.domain.consumer;

public interface Payment {
    public void setCardNumber(String cardNumber);
    public String getCardNumber();
    public void setCardName(String cardName);
    public String getCardName();
    public void setCVV( String CVV); //you had cvv as double... //I never see decimal points as cvvs or large cvvs..... 
    public String getCVV(); //yup ^
}
