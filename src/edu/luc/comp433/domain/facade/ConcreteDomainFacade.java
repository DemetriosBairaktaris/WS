package edu.luc.comp433.domain.facade;

public class ConcreteDomainFacade implements DomainFacade {

  public ConcreteDomainFacade() {}
  
  @Override
  public String getProduct(String productName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean acceptBuyOrder(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean acceptPayment(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean shipOrder(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getOrderStatus(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean cancelOrder(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean registerPartner(String userName, String name, String address, String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updatePartnerName(String userName, String name) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updatePartnerAddress(String userName, String address) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updatePartnerPhone(String userName, String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addProduct(String userName, String name, String desc, double cost, String company,
      long stock) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getOrders(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getOrderFulfillment(String userName, String orderID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean registerConsumer(String userName, String firstName, String lastName,
      String address, String phone, String cardName, String cardNumber, String CVV) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateConsumerName(String userName, String firstName, String lastName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateConsumerAddress(String userName, String address) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateConsumerPhone(String userName, String phone) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateCreditCard(String userName, String cardName, String cardNumber, String CVV) {
    // TODO Auto-generated method stub
    return false;
  }

}
