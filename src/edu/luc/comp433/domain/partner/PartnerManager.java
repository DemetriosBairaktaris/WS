package edu.luc.comp433.domain.partner;

import java.sql.SQLException;

import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

public interface PartnerManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean create(String userName, String name, String address, String phone);
  public boolean updateName(String userName, String name);
  public boolean updateAddress(String userName, String address);
  public boolean updatePhone(String userName, String phone);
  public boolean delete(String userName);
  public PartnerProfile getPartnerProfile(String userName);
  public boolean addProduct(String userName, String name, String desc, double cost, long stock) throws SQLException, Exception;
  public boolean updateDescription(String userName, String desc, String name) throws SQLException, Exception;
  public boolean updateCost(String userName, double cost, String name) throws SQLException, Exception;
  public boolean updateStock(String userName, long stock, String name) throws SQLException, Exception;
  public boolean removeProduct(String userName, String name) throws SQLException, Exception;
  public Product getProduct(String name);
}
