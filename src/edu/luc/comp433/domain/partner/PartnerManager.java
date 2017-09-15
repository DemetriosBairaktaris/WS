package edu.luc.comp433.domain.partner;

import edu.luc.comp433.dao.DatabaseAccess;

public interface PartnerManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean create(String userName, String name, String address, String phone);
  public boolean updateName(String userName, String name);
  public boolean updateAddress(String userName, String address);
  public boolean updatePhone(String userName, String phone);
  public boolean delete(String userName);
  public PartnerProfile getPartnerProfile(String userName);
}
