package edu.luc.comp433.service;

import java.sql.SQLException;
import java.util.Set;

import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.OrderRequest;

public interface OrderService {

	OrderRepresentation getOrder(int orderId);

	Set<OrderRepresentation> getOrdersFromPartner(String partnerUserName);

	void deleteOrder(int orderId);

	OrderRepresentation insertOrder(Set<OrderRequest> request) throws SQLException;

}
