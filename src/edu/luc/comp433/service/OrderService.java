package edu.luc.comp433.service;

import java.util.Set;

import edu.luc.comp433.service.representation.OrderRepresentation;

public interface OrderService {

	OrderRepresentation getOrder(int orderId);

	Set<OrderRepresentation> getOrdersFromPartner(String partnerUserName);

	void deleteOrder(int orderId);

}
