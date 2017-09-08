package edu.luc.comp433.dao;

import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public class ConcreteDatabaseAccess implements DatabaseAccess {

	@Override
	public boolean insertOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrderDetail(OrderDetail detail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getOrder(double orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> getOrderDetails(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> getOrderDetails(double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPartner(PartnerProfile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePartner(PartnerProfile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePartner(PartnerProfile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PartnerProfile getPartnerProfile(double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

}
