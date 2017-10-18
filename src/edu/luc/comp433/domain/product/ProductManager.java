package edu.luc.comp433.domain.product;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;

/**
 * Interface for the product manager.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface ProductManager {

  /**
   * Retrieves the database object.
   * 
   * @return DatabaseAccess
   */
  public DatabaseAccess getDatabase();

  /**
   * Sets the database object.
   * 
   * @param database
   *          DatabaseAccess
   */
  public void setDatabase(DatabaseAccess database);

  /**
   * Adds a product to the database.
   * 
   * @param name
   *          String
   * @param desc
   *          String
   * @param cost
   *          double
   * @param stock
   *          long
   * @param companyName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   */
  public boolean addProduct(String name, String desc, double cost, long stock, String companyName) throws SQLException;

  /**
   * Deletes the product from the database.
   * 
   * @param companyName
   *          String
   * @param name
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean deleteProduct(String companyName, String name) throws SQLException, Exception;

  /**
   * Updates a product's stock for a specific company.
   * 
   * @param companyName
   *          String
   * @param name
   *          String
   * @param stock
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updateStock(String companyName, String name, long stock) throws SQLException, Exception;

  /**
   * Updates a product's stock for a specific company.
   * 
   * @param companyName
   *          String
   * @param name
   *          String
   * @param cost
   *          double
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updateCost(String companyName, String name, double cost) throws SQLException, Exception;

  /**
   * Retrieves all the products of a specific name.
   * 
   * @param name
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<Product> getProducts(String name) throws SQLException, Exception;

  /**
   * Adds a review for a specific product.
   * 
   * @param companyName
   *          String
   * @param name
   *          String
   * @param review
   *          String
   * @param rating
   *          integer
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean addReview(String companyName, String name, String review, int rating) throws SQLException, Exception;

  /**
   * Retrieves all the reviews for a specific product.
   * 
   * @param companyName
   *          String
   * @param name
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<Review> getReviews(String companyName, String name) throws SQLException, Exception;

  /**
   * Retrieves the products for a single company.
   * 
   * @param companyName
   *          String
   * @return List
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public List<Product> getCompanyProducts(String companyName) throws SQLException, Exception;

  /**
   * Retrieves a product from a specific partner.
   * 
   * @param name
   *          String
   * @param partnerUserName
   *          String
   * @return Product
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public Product getProductFromPartner(String name, String partnerUserName) throws SQLException, Exception;

}
