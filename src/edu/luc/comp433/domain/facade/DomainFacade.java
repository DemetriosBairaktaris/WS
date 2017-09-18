package edu.luc.comp433.domain.facade;

public interface DomainFacade {
  public String getProduct(String productName);
  public boolean acceptBuyOrder(String userName, String orderID);
  public boolean acceptPayment(String userName, String orderID);
  public boolean shipOrder(String userName, String orderID);
  public boolean getOrderStatus(String userName, String orderID);
  public boolean cancelOrder(String userName, String orderID);
  public boolean registerPartner(String userName, String name, String address, String phone);
  public boolean updatePartnerName(String userName, String name);
  public boolean updatePartnerAddress(String userName, String address);
  public boolean updatePartnerPhone(String userName, String phone);
  public boolean addProduct(String userName, String name, String desc, double cost, String company, long stock);
  public String getOrders(String userName);
  public boolean getOrderFulfillment(String userName, String orderID);
  public boolean registerConsumer(String userName, String firstName, String lastName, String address, String phone, 
      String cardName, String cardNumber, String CVV);
  public boolean updateConsumerName(String userName, String firstName, String lastName);
  public boolean updateConsumerAddress(String userName, String address);
  public boolean updateConsumerPhone(String userName, String phone);
  public boolean updateCreditCard(String userName, String cardName, String cardNumber, String CVV);
}
