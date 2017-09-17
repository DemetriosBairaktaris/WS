package edu.luc.comp433.domain.product;

import edu.luc.comp433.dao.DatabaseAccess;

public interface ProductManager {
  public void setDatabase(DatabaseAccess database);
  public DatabaseAccess getDatabase();
  public boolean addProduct(String name, String desc, double cost, String company, long stock);
  public boolean updateDescription(String desc, String name);
  public boolean updateCost(double cost, String name);
  public boolean updateStock(long stock, String name);
  public boolean removeProduct(String name);
  public Product getProduct(String name);
}
