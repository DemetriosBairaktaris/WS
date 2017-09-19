package edu.luc.comp433.domain.facade;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.partner.PartnerManager;

public class ConcreteDomainFacade implements DomainFacade {

  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private CustomerManager consumers = (CustomerManager) context.getBean("consumerManager");
  private PartnerManager partners = (PartnerManager) context.getBean("partnerManager");
  
  public ConcreteDomainFacade() {}
  
  @Override
  public String getProduct(String productName) {
    return partners.getProduct(productName).getName();
  }

  @Override
  public boolean acceptBuyOrder(String userName, double orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean acceptPayment(String userName, double orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean shipOrder(String userName, double orderID) throws NumberFormatException, SQLException {
    consumers.getCustomer(userName).getOrders().get((int) orderID).setStatus("shipped");
    if (consumers.getCustomer(userName).getOrders().get((int) orderID).getStatus().equals("shipped")) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getOrderStatus(String userName, double orderID) throws NumberFormatException, SQLException {
    return consumers.getCustomer(userName).getOrders().get((int) orderID).getStatus();
  }

  //TODO fix this
  @Override
  public boolean cancelOrder(String userName, double orderID) throws SQLException {
    for (int i = 0; i < consumers.getCustomer(userName).getOrders().size(); i++) {
      if (consumers.getCustomer(userName).getOrders().get(i).getID() == orderID) {
        consumers.getCustomer(userName).removeOrder(consumers.getCustomer(userName).getOrders().get(i));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean registerPartner(String userName, String name, String address, String phone) throws SQLException {
    if (partners.create(userName, name, address, phone)) {
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
  public boolean updatePartnerAddress(String userName, String address) throws SQLException {
    if (partners.updateAddress(userName, address)) {
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
  public String getOrders(String userName) {
    //TODO add user name checks here
    return null;
  }

  @Override
  public boolean getOrderFulfillment(String userName, String orderID) {
    // TODO add user name checks here
    return false;
  }

  @Override
  public boolean registerConsumer(String userName, String firstName, String lastName,
      String address, String phone, String cardName, String cardNumber, String CVV) throws SQLException {
    if (consumers.create(userName, firstName, lastName, address, phone, cardName, cardNumber, CVV)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerName(String userName, String firstName, String lastName) throws SQLException {
    if (consumers.updateName(userName, firstName, lastName)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerAddress(String userName, String address) throws SQLException {
    if (consumers.updateAddress(userName, address)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateConsumerPhone(String userName, String phone) throws SQLException {
    if (consumers.updatePhone(userName, phone)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException {
    if (consumers.updatePayment(userName, cardName, cardNumber, CVV)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean addProduct(String userName, String name, String desc, double cost, String company,
      long stock) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }
}
