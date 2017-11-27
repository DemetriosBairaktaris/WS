package edu.luc.comp433.domain.partner;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;

/**
 * Lays out the needs of the partner manager.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface PartnerManager {

  /**
   * Creates a partner profile.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @param address
   *          String
   * @param phone
   *          String
   * @param password
   *          String
   *          
   * @return true if successful
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public boolean createPartner(String userName, String name, String address, String phone, String password)
      throws SQLException, Exception;

  /**
   * Deletes a partner profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean deletePartner(String userName) throws SQLException, Exception;

  /**
   * Retrieves the database access object.
   * 
   * @return DatabaseAccess
   */
  public DatabaseAccess getDatabase();

  /**
   * Retrieves a specific partner profile.
   * 
   * @param userName
   *          String
   * @return PartnerProfile
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public PartnerProfile getPartnerProfile(String userName) throws SQLException, Exception;

  /**
   * Sets the database.
   * 
   * @param database
   *          DatabaseAccess
   */
  public void setDatabase(DatabaseAccess database);

  /**
   * Updates a partner's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateAddress(String userName, String address) throws SQLException, Exception;

  /**
   * Updates a partner's name.
   * 
   * @param userName
   *          String
   * @param name
   *          String
   * @return true if successful
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updateName(String userName, String name) throws SQLException, Exception;

  /**
   * Updates a partner's phone number.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws Exception
   *           thrown by DB
   * @throws SQLException
   *           thrown by DB
   */
  public boolean updatePhone(String userName, String phone) throws SQLException, Exception;

  /**
   * Returns all orders that a partner's product was involved with.
   * 
   * @param userName
   *          String
   * @return List
   * @throws Exception
   *           thrown by DB
   */
  public List<Order> getOrdersFromPartner(String userName) throws Exception;

  
}
