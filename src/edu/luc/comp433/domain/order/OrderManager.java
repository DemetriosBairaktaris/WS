package edu.luc.comp433.domain.order;

public interface OrderManager {
    public boolean createOrder();
    public boolean updateOrder();
    public boolean closeOrder();
    public boolean cancelOrder();
}
