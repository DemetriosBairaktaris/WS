package edu.luc.comp433.domain.partner;

/**
 * Lays out the needs for a partner profile.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface PartnerProfile {

  /**
   * Retrieves the partner's address.
   * 
   * @return String
   */
  public String getAddress();

  /**
   * Retrieves the partner's name.
   * 
   * @return String
   */
  public String getName();

  /**
   * Retrieves the partner's phone number.
   * 
   * @return String
   */
  public String getPhone();

  /**
   * Retrieves the partner's user name.
   * 
   * @return String
   */
  public String getUserName();

  /**
   * Sets this partner's address.
   * 
   * @param address
   *          String
   */
  public void setAddress(String address);

  /**
   * Sets this partner's name.
   * 
   * @param name
   *          String
   */
  public void setName(String name);

  /**
   * Sets this partner's phone number.
   * 
   * @param phone
   *          String
   */
  public void setPhone(String phone);

  /**
   * Sets the partner's user name.
   * 
   * @param userName
   *          String
   */
  public void setUserName(String userName);
  
  /**
   * 
   * @param password
   *          String
   */
  public void setPassword(String password);
  
   /**
    * 
    * @return password
    *           String
    */
  public String getPassword() ;
}
