package edu.luc.comp433.domain.product;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;

public interface ProductManager {

  public DatabaseAccess getDatabase();

  public void setDatabase(DatabaseAccess database);

  public boolean addProduct(String name, String desc, double cost, long stock, String companyName)
      throws SQLException;

  public boolean deleteProduct(String name) throws SQLException;

  public boolean updateStock(String name, long stock) throws SQLException;

  public boolean updateCost(String name, double cost) throws SQLException;

  public Product getProduct(String name) throws SQLException;

  public boolean addReview(String name, Review review) throws SQLException;

  public List<Review> getReviews(String name) throws SQLException;

  public List<Product> getCompanyProducts(String companyName);
}
