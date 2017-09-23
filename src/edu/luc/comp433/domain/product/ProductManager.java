package edu.luc.comp433.domain.product;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;

public interface ProductManager {

  public DatabaseAccess getDatabase();

  public void setDatabase(DatabaseAccess database);

  public boolean addProduct(String name, String desc, double cost, long stock, String companyName)
      throws SQLException;

  public boolean deleteProduct(String companyName, String name) throws SQLException;

  public boolean updateStock(String companyName, String name, long stock) throws SQLException, Exception;

  public boolean updateCost(String companyName, String name, double cost) throws SQLException, Exception;

  public List<Product> getProducts(String name) throws SQLException;

  public boolean addReview(String companyName, String name, Review review) throws SQLException, Exception;

  public List<Review> getReviews(String companyName, String name) throws SQLException, Exception;

  public List<Product> getCompanyProducts(String companyName);

Product getProductFromPartner(String name, String partnerUserName) throws SQLException, Exception;
}
