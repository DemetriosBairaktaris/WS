package edu.luc.comp433.service.workflow;

import edu.luc.comp433.domain.customer.CustomerManager;

/**
 * Interface for the customer activity methods.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface CustomerActivity {

  /**
   * Sets the customer manager.
   * 
   * @param customers
   *          CustomerManager
   */
  public void setCustomers(CustomerManager customers);

  /**
   * Retrieves the customer manager.
   * 
   * @return CustomerManager
   */
  public CustomerManager getCustomers();

}
