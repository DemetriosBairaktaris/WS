package edu.luc.comp433.service.workflow;

import edu.luc.comp433.domain.customer.CustomerManager;

public class ConcreteCustomerActivity implements CustomerActivity {

  private CustomerManager customers;

  public ConcreteCustomerActivity() {
  }

  @Override
  public CustomerManager getCustomers() {
    return customers;
  }

  @Override
  public void setCustomers(CustomerManager customers) {
    this.customers = customers;
  }

}
