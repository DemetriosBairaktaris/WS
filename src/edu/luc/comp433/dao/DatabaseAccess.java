package edu.luc.comp433.dao;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public interface DatabaseAccess {

	/**
	 * Order Related Methods
	 */
	public boolean insertOrder(); // don't know what params or return will be, yet

	public boolean updateOrderDetail(OrderDetail detail);

	public Order getOrder(double orderId);

	public List<OrderDetail> getOrderDetails(Order order);

	public List<OrderDetail> getOrderDetails(double id);

	/**
	 * Partner Related Methods
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean insertPartner(PartnerProfile profile) throws SQLException, Exception;

	public boolean updatePartner(PartnerProfile profile) throws SQLException, Exception;

	public boolean deletePartner(PartnerProfile profile) throws SQLException;

	public PartnerProfile getPartnerProfile(String name) throws Exception, SQLException;

	/**
	 * Product Related Methods
	 * 
	 * @throws SQLException
	 */
	public boolean insertProduct(Product product, PartnerProfile profile) throws SQLException;

	public boolean updateProduct(Product product, PartnerProfile profile) throws SQLException;

	public List<Product> getProduct(String productName, PartnerProfile profile) throws SQLException;

	boolean deleteProduct(Product product, PartnerProfile profile) throws SQLException;

	/**
	 * Consumer Methods
	 * 
	 * @throws SQLException
	 */
	public boolean insertConsumer(Customer consumer) throws SQLException;

	public boolean updateConsumer(Customer consumer) throws SQLException;

	public boolean deleteConsumer(Customer consumer) throws SQLException;

	public Customer getConsumer(String userName) throws SQLException;

}
