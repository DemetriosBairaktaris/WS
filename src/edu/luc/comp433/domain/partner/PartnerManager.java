package edu.luc.comp433.domain.partner;

import java.sql.SQLException;

import edu.luc.comp433.dal.DatabaseAccess;

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
   * @return true if successful
   * @throws Exception
   * @throws SQLException
   */
  public boolean createPartner(String userName, String name, String address, String phone)
      throws SQLException, Exception;

  /**
   * Deletes a partner profile.
   * 
   * @param userName
   *          String
   * @return true if successful
   * @throws SQLException
   * @throws Exception
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
   * @throws SQLException
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
   * @throws SQLException
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
   * @throws SQLException
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
   * @throws SQLException
   */
  public boolean updatePhone(String userName, String phone) throws SQLException, Exception;
}
