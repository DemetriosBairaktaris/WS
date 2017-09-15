package edu.luc.comp433.domain.consumer;

import edu.luc.comp433.dao.DatabaseAccess;

public interface ConsumerManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean create(String userName, String firstName, String lastName);
  public boolean updateAddress(String userName, Address address);
  public boolean updatePhone(String userName, Phone phone);
  public boolean updatePayment(String userName, Payment payment);
  public boolean delete(String userName);
}
