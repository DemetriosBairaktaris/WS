package edu.luc.comp433.domain.customer;

/**
 * Details the set up of a customer object.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface Customer {

  /**
   * Retrieves the customer's address.
   * 
   * @return String
   */
  public String getAddress();

  /**
   * Retrieves the customer's first name.
   * 
   * @return String
   */
  public String getFirstName();

  /**
   * Retrieves the customer's last name.
   * 
   * @return String
   */
  public String getLastName();

  /**
   * Retrieves the customer's payment information.
   * 
   * @return Payment
   */
  public Payment getPayment();

  /**
   * Retrieves the customer's phone number.
   * 
   * @return String
   */
  public String getPhone();

  /**
   * Retrieves the customer's user name.
   * 
   * @return String
   */
  public String getUserName();

  /**
   * Sets the customer's address.
   * 
   * @param address
   *          String
   */
  public void setAddress(String address);

  /**
   * Sets the customer's first name.
   * 
   * @param firstName
   *          String
   */
  public void setFirstName(String firstName);

  /**
   * Sets the customer's last name.
   * 
   * @param lastName
   *          String
   */
  public void setLastName(String lastName);

  /**
   * Sets the customer's payment information.
   * 
   * @param payment
   *          Payment
   */
  public void setPayment(Payment payment);

  /**
   * Sets the customer's phone number.
   * 
   * @param phone
   *          String
   */
  public void setPhone(String phone);

  /**
   * Sets the customer's user name.
   * 
   * @param userName
   *          String
   */
  public void setUserName(String userName);
}
