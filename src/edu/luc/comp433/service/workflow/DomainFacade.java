package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import edu.luc.comp433.domain.customer.CustomerManager;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.product.ProductManager;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.ProductRepresentation;

/**
 * This class sets up the facade between the domain layer and the service layer.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public interface DomainFacade {

	/**
	 * Sets the customer manager.
	 * 
	 * @param customers
	 *            CustomerManager
	 */
	public void setCustomers(CustomerManager customers);

	/**
	 * Retrieves the customer manager.
	 * 
	 * @return CustomerManager
	 */
	public CustomerManager getCustomers();

	/**
	 * Sets the partner manager.
	 * 
	 * @param partners
	 *            PartnerManager
	 */
	public void setPartners(PartnerManager partners);

	/**
	 * Retrieves the partner manager.
	 * 
	 * @return PartnerManager
	 */
	public PartnerManager getPartners();

	/**
	 * Sets the order manager.
	 * 
	 * @param orders
	 *            OrderManager
	 */
	public void setOrders(OrderManager orders);

	/**
	 * Retrieves the order manager.
	 * 
	 * @return OrderManager
	 */
	public OrderManager getOrders();

	/**
	 * Sets the product manager.
	 * 
	 * @param products
	 *            ProductManager
	 */
	public void setProducts(ProductManager products);

	/**
	 * Retrieves the product manager.
	 * 
	 * @return ProductManager
	 */
	public ProductManager getProducts();

	/**
	 * Searches for products by name.
	 * 
	 * @param productName
	 *            String
	 * @return List
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public List<ProductRepresentation> searchProduct(String productName) throws SQLException, Exception;

	/**
	 * Checks if a product is available.
	 * 
	 * @param productName
	 *            String
	 * @return true if available
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean checkAvailability(String productName) throws SQLException, Exception;

	/**
	 * Purchases a product.
	 * 
	 * @param customerName
	 *            String
	 * @param productName
	 *            String
	 * @param quantity
	 *            long
	 * @param orderId
	 *            integer
	 * @return integer
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public int buyProduct(String customerName, String productName, long quantity, int orderId)
			throws SQLException, Exception;

	/**
	 * Changes the status of an order to fulfilled.
	 * 
	 * @param orderId
	 *            integer
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean fulfillOrder(int orderId) throws SQLException, Exception;

	/**
	 * Cancels an order and refunds the money.
	 * 
	 * @param orderId
	 *            integer
	 * @return integer
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public int cancelOrder(int orderId) throws SQLException, Exception;

	/**
	 * Ships an order.
	 * 
	 * @param orderId
	 *            integer
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean shipOrder(int orderId) throws SQLException, Exception;

	/**
	 * Retrieves an order's status.
	 * 
	 * @param orderId
	 *            integer
	 * @return String
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public String getOrderStatus(int orderId) throws SQLException, Exception;

	/**
	 * Adds a customer profile to the system.
	 * 
	 * @param userName
	 *            String
	 * @param firstName
	 *            String
	 * @param lastName
	 *            String
	 * @param address
	 *            String
	 * @param phone
	 *            String
	 * @param cardName
	 *            String
	 * @param cardNumber
	 *            String
	 * @param cvv
	 *            String
	 * @param expiration
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws ParseException
	 *             thrown by parsing the expiration
	 */
	public boolean addCustomer(String userName, String firstName, String lastName, String address, String phone,
			String cardName, String cardNumber, String cvv, String expiration) throws SQLException, ParseException;

	/**
	 * Checks if a customer profile is active.
	 * 
	 * @param userName
	 *            String
	 * @return true if profile exists.
	 * @throws SQLException
	 *             thrown by DB
	 */
	public boolean checkCustomerStatus(String userName) throws SQLException;

	/**
	 * Deletes a customer profile.
	 * 
	 * @param userName
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 */
	public boolean deleteCustomer(String userName) throws SQLException;

	/**
	 * Updates a customer's name.
	 * 
	 * @param userName
	 *            String
	 * @param firstName
	 *            String
	 * @param lastName
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 */
	public boolean updateCustomerName(String userName, String firstName, String lastName) throws SQLException;

	/**
	 * Updates a customer's address.
	 * 
	 * @param userName
	 *            String
	 * @param address
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 */
	public boolean updateCustomerAddress(String userName, String address) throws SQLException;

	/**
	 * Update the customer's phone number.
	 * 
	 * @param userName
	 *            String
	 * @param phone
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 */
	public boolean updateCustomerPhone(String userName, String phone) throws SQLException;

	/**
	 * Updates the customer's payment info.
	 * 
	 * @param userName
	 *            String
	 * @param cardName
	 *            String
	 * @param cardNumber
	 *            String
	 * @param cvv
	 *            String
	 * @param expiration
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws ParseException
	 *             thrown by parsing expiration into date
	 */
	public boolean updatePaymentInfo(String userName, String cardName, String cardNumber, String cvv, String expiration)
			throws SQLException, ParseException;

	/**
	 * Adds a review to a specific product.
	 * 
	 * @param userName
	 *            String
	 * @param productName
	 *            String
	 * @param review
	 *            String
	 * @param rating
	 *            integer
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean addReview(String userName, String productName, String review, int rating)
			throws SQLException, Exception;

	/**
	 * Adds a partner profile.
	 * 
	 * @param userName
	 *            String
	 * @param companyName
	 *            String
	 * @param address
	 *            String
	 * @param phone
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean addPartner(String userName, String companyName, String address, String phone)
			throws SQLException, Exception;

	/**
	 * Deletes a partner profile.
	 * 
	 * @param userName
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean deletePartner(String userName) throws SQLException, Exception;

	/**
	 * Accept a partner's product.
	 * 
	 * @param userName
	 *            String
	 * @param productName
	 *            String
	 * @param productDesc
	 *            String
	 * @param cost
	 *            double
	 * @param stock
	 *            long
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean acceptPartnerProduct(String userName, String productName, String productDesc, double cost,
			long stock) throws SQLException, Exception;

	/**
	 * Updates a partner's name.
	 * 
	 * @param userName
	 *            String
	 * @param companyName
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean updatePartnerName(String userName, String companyName) throws SQLException, Exception;

	/**
	 * Updates a partner's address.
	 * 
	 * @param userName
	 *            String
	 * @param address
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean updatePartnerAddress(String userName, String address) throws SQLException, Exception;

	/**
	 * Update partner's phone.
	 * 
	 * @param userName
	 *            String
	 * @param phone
	 *            String
	 * @return true if successful
	 * @throws SQLException
	 *             thrown by DB
	 * @throws Exception
	 *             thrown by DB
	 */
	public boolean updatePartnerPhone(String userName, String phone) throws SQLException, Exception;

	/**
	 * Retrieves all the partner's sales.
	 * 
	 * @param userName
	 *            String
	 * @return String
	 * @throws Exception
	 *             thrown by DB
	 */
	public String getPartnerSales(String userName) throws Exception;

	/**
	 * Gets product from a partner.
	 * 
	 * @param productName
	 * @param partnerUserName
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ProductRepresentation getProductFromPartner(String productName, String partnerUserName)
			throws SQLException, Exception;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public PartnerRepresentation getPartnerByUserName(String userName) throws SQLException, Exception;

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	OrderRepresentation getOrderById(int orderId);

	/**
	 * 
	 * @param partnerUserName
	 * @return
	 */
	List<OrderRepresentation> getOrdersFromPartner(String partnerUserName);
}
