package edu.luc.comp433.domain.partner;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.product.Product;

import java.sql.SQLException;

public interface PartnerManager {
  public boolean addProduct(String userName, String name, String desc, double cost, long stock)
      throws SQLException, Exception;

  public boolean create(String userName, String name, String address, String phone);

  public boolean delete(String userName);

  public DatabaseAccess getDatabase();

  public PartnerProfile getPartnerProfile(String userName);

  public Product getProduct(String name);

  public boolean removeProduct(String userName, String name) throws SQLException, Exception;

  public void setDatabase(DatabaseAccess database);

  public boolean updateAddress(String userName, String address);

  public boolean updateCost(String userName, double cost, String name)
      throws SQLException, Exception;

  public boolean updateDescription(String userName, String desc, String name)
      throws SQLException, Exception;

  public boolean updateName(String userName, String name);

  public boolean updatePhone(String userName, String phone);

  public boolean updateStock(String userName, long stock, String name)
      throws SQLException, Exception;
}
