package edu.luc.comp433.domain.customer;

import java.util.Date;

/**
 * Customer Payment Profile.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Payment {

  /**
   * Retrives the name on the credit card.
   * 
   * @return String
   */
  public String getCardName();

  /**
   * Retrieves the credit card number.
   * 
   * @return String
   */
  public String getCardNumber();

  /**
   * Retrieves the credit card's CVV.
   * 
   * @return String
   */
  public String getCvv();

  /**
   * Sets the name on the credit card.
   * 
   * @param cardName
   *          String
   */
  public void setCardName(String cardName);

  /**
   * Sets the number for the credit card.
   * 
   * @param cardNumber
   *          String
   */
  public void setCardNumber(String cardNumber);

  /**
   * Sets the credit card's CVV number.
   * 
   * @param cvv
   *          String
   */
  public void setCvv(String cvv);

  /**
   * Sets the expiration date of the card.
   * 
   * @param expiration
   *          Date
   */
  public void setExpiration(Date expiration);

  /**
   * Retrieves the expiration date of the card.
   * 
   * @return Date
   */
  public Date getExpiration();
}
