package edu.luc.comp433.domain.facade;

import java.sql.SQLException;

public interface DomainFacade {
  public String getProduct(String productName) throws SQLException;
  public boolean acceptBuyOrder(String userName, double orderID) throws SQLException;
  public boolean acceptPayment(String userName, double orderID) throws SQLException;
  public boolean shipOrder(String userName, double orderID) throws SQLException;
  public String getOrderStatus(String userName, double orderID) throws SQLException;
  public boolean cancelOrder(String userName, double orderID) throws SQLException;
  public boolean registerPartner(String userName, String name, String address, String phone) throws SQLException;
  public boolean updatePartnerName(String userName, String name) throws SQLException;
  public boolean updatePartnerAddress(String userName, String address) throws SQLException;
  public boolean updatePartnerPhone(String userName, String phone) throws SQLException;
  public boolean addProduct(String userName, String name, String desc, double cost, String company, long stock) throws SQLException;
  public String getOrders(String userName) throws SQLException;
  public boolean getOrderFulfillment(String userName, String orderID) throws SQLException;
  public boolean registerConsumer(String userName, String firstName, String lastName, String address, String phone, 
      String cardName, String cardNumber, String CVV) throws SQLException;
  public boolean updateConsumerName(String userName, String firstName, String lastName) throws SQLException;
  public boolean updateConsumerAddress(String userName, String address) throws SQLException;
  public boolean updateConsumerPhone(String userName, String phone) throws SQLException;
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException;
}
