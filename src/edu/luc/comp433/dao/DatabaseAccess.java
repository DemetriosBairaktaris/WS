package edu.luc.comp433.dao;

import java.sql.SQLException;
import java.util.List;

import edu.luc.comp433.domain.consumer.Consumer;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public interface DatabaseAccess {
  //define methods that the various domain managers will need to access
	
   /**
    * Order Related Methods 	
    */
    public boolean insertOrder(); //don't know what params or return will be, yet
    public boolean updateOrderDetail(OrderDetail detail);
    public Order getOrder(double orderId);
    public List<OrderDetail> getOrderDetails(Order order);
    public List<OrderDetail> getOrderDetails(double id);
  
    /**
     * Partner Related Methods
     * @throws SQLException 
     * @throws Exception 
     */
    public boolean insertPartner(PartnerProfile profile) throws SQLException, Exception;
    public boolean updatePartner(PartnerProfile profile) throws SQLException;
    public boolean deletePartner(PartnerProfile profile) throws SQLException;
    public PartnerProfile getPartnerProfile(String name) throws Exception, SQLException;
    
    /**
     * Product Related Methods
     */
    public boolean insertProduct(Product product);
    public boolean updateProduct(Product product);
	
    /**
     * Consumer Methods
     * @throws SQLException 
     */
	public boolean insertConsumer(Consumer consumer) throws SQLException;
	public boolean updateConsumer(Consumer consumer);
	public boolean deleteConsumer(Consumer consumer);
	public Consumer getConsumer(String userName);
}
