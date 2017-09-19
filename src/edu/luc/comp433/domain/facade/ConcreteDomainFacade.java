package edu.luc.comp433.domain.facade;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.partner.PartnerManager;

import java.sql.SQLException;

public class ConcreteDomainFacade implements DomainFacade {

  private CustomerManager customers;
  private PartnerManager partners;

  public ConcreteDomainFacade() {
  }

  @Override
  public boolean acceptBuyOrder(String userName, double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean acceptPayment(String userName, double orderId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addProduct(String userName, String name, String desc, double cost, String company,
      long stock) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  // TODO fix this
  @Override
  public boolean cancelOrder(String userName, double orderId) throws SQLException {
    for (int i = 0; i < customers.getCustomer(userName).getOrders().size(); i++) {
      if (customers.getCustomer(userName).getOrders().get(i).getId() == orderId) {
        customers.getCustomer(userName)
            .removeOrder(customers.getCustomer(userName).getOrders().get(i));
        return true;
      }
    }
    return false;
  }

  @Override
  public CustomerManager getCustomerManager() {
    return customers;
  }

  @Override
  public boolean getOrderFulfillment(String userName, String orderId) {
    // TODO add user name checks here
    return false;
  }

  @Override
  public String getOrders(String userName) {
    // TODO add user name checks here
    return null;
  }

  @Override
  public String getOrderStatus(String userName, double orderId)
      throws NumberFormatException, SQLException {
    return customers.getCustomer(userName).getOrders().get((int) orderId).getStatus();
  }

  @Override
  public PartnerManager getPartnerManager() {
    return partners;
  }

  @Override
  public String getProduct(String productName) {
    return partners.getProduct(productName).getName();
  }

  @Override
  public boolean registerConsumer(String userName, String firstName, String lastName,
      String address, String phone, String cardName, String cardNumber, String cvv)
      throws SQLException {
    if (customers.create(userName, firstName, lastName, address, phone, cardName, cardNumber,
        cvv)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean registerPartner(String userName, String name, String address, String phone)
      throws SQLException {
    if (partners.create(userName, name, address, phone)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void setCustomerManager(CustomerManager customers) {
    this.customers = customers;
  }

  @Override
  public void setPartnerManager(PartnerManager partners) {
    this.partners = partners;
  }

  @Override
  public boolean shipOrder(String userName, double orderId)
      throws NumberFormatException, SQLException {
    customers.getCustomer(userName).getOrders().get((int) orderId).setStatus("shipped");
    if (customers.getCustomer(userName).getOrders().get((int) orderId).getStatus()
        .equals("shipped")) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerAddress(String userName, String address) throws SQLException {
    if (customers.updateAddress(userName, address)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerName(String userName, String firstName, String lastName)
      throws SQLException {
    if (customers.updateName(userName, firstName, lastName)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerPhone(String userName, String phone) throws SQLException {
    if (customers.updatePhone(userName, phone)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePartnerAddress(String userName, String address) throws SQLException {
    if (partners.updateAddress(userName, address)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePartnerName(String userName, String name) throws SQLException {
    if (partners.updateName(userName, name)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePartnerPhone(String userName, String phone) throws SQLException {
    if (partners.updatePhone(userName, phone)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePayment(String userName, String cardName, String cardNumber, String cvv)
      throws SQLException {
    if (customers.updatePayment(userName, cardName, cardNumber, cvv)) {
      return true;
    } else {
      return false;
    }
  }
}
