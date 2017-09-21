package edu.luc.comp433.domain.product;

import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;

public interface ProductManager {

  public DatabaseAccess getDatabase();
  
  public void setDatabase(DatabaseAccess database);
  
  public boolean addProduct(String name, String desc, double cost, long stock, String companyName);
  
  public boolean deleteProduct(String name);
  
  public boolean updateStock(String name, long stock);
  
  public boolean updateCost(String name, double cost);
  
  public Product getProduct(String name);
  
  public boolean addReview(String name, Review review);
  
  public List<Review> getReviews(String name);
  
  public List<Product> getCompanyProducts(String companyName);
}
