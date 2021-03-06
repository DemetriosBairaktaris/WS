package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRepresentation;

/**
 * Activity for partner methods.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface PartnerActivity {

  /**
   * Sets the partner manager.
   * 
   * @param partners
   *          PartnerManager
   */
  public void setPartners(PartnerManager partners);

  /**
   * Retrieves the partner manager.
   * 
   * @return PartnerManager
   */
  public PartnerManager getPartners();

  /**
   * Adds a partner profile.
   * 
   * @param userName
   *          String
   * @param companyName
   *          String
   * @param address
   *          String
   * @param phone
   *          String
   * @param password
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean addPartner(String userName, String companyName, String address, String phone, String password)
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
   * Updates a partner's name.
   * 
   * @param userName
   *          String
   * @param companyName
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updatePartnerName(String userName, String companyName) throws SQLException, Exception;

  /**
   * Updates a partner's address.
   * 
   * @param userName
   *          String
   * @param address
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updatePartnerAddress(String userName, String address) throws SQLException, Exception;

  /**
   * Update partner's phone.
   * 
   * @param userName
   *          String
   * @param phone
   *          String
   * @return true if successful
   * @throws SQLException
   *           thrown by DB
   * @throws Exception
   *           thrown by DB
   */
  public boolean updatePartnerPhone(String userName, String phone) throws SQLException, Exception;

  /**
   * Retrieves a partner representation by user name.
   * 
   * @param userName
   *          String
   * @return PartnerRepresentation
   * @throws SQLException
   *           Database exception
   * @throws Exception
   *           all other exceptions
   */
  public PartnerRepresentation getPartnerByUserName(String userName) throws SQLException, Exception;

  /**
   * Assembles a partner representation.
   * 
   * @param partner
   *          PartnerProfile
   * @return PartnerRepresentation
   */
  public PartnerRepresentation assemblePartnerToRepresentation(PartnerProfile partner);

  /**
   * Get sales for partners.
   * 
   * @param userName
   *          String
   * @return String
   * @throws Exception
   *           thrown if issues
   */
  public String getPartnerSales(String userName) throws Exception;

  /**
   * Retrieves orders from a partner user name.
   * 
   * @param partnerUserName
   *          String
   * @return List of order representations
   */
  public List<OrderRepresentation> getOrdersFromPartner(String partnerUserName);

  /**
   * Method to assemble order from below.
   * 
   * @param order
   *          Order
   * @return OrderRepresentation
   */
  public OrderRepresentation assembleOrderToRepresentation(Order order);

  /**
   * Checks the partner login.
   * 
   * @param userName
   *          String
   * @param password
   *          String
   * @return true if successful
   * @throws Exception
   *           thrown if issues
   */
  public boolean checkLogin(String userName, String password) throws Exception;
}
