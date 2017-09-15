package edu.luc.comp433.domain.product;

public interface ProductManager {
    public boolean addProduct(String name, String desc);
    public boolean updateProduct(String desc, double id);
    public boolean updateProduct(double cost, double id);
    public boolean updateProduct(long stock, double id);
    public boolean removeProduct(double id);
    public Product getProduct(String name, String userName);
}
