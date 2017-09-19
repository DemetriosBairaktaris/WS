package edu.luc.comp433.domain.consumer;

import java.sql.SQLException;

import edu.luc.comp433.dao.DatabaseAccess;

public interface ConsumerManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean create(String userName, String firstName, String lastName, String address, String phone,
      String cardName, String cardNumber, String CVV) throws SQLException;
  public boolean updateName(String userName, String firstName, String lastName) throws SQLException;
  public boolean updateAddress(String userName, String address) throws SQLException;
  public boolean updatePhone(String userName, String phone) throws SQLException;
  public boolean updatePayment(String userName, String cardName, String cardNumber, String CVV) throws SQLException;
  public boolean delete(String userName) throws SQLException;
  public Consumer getConsumer(String userName) throws SQLException;
}
