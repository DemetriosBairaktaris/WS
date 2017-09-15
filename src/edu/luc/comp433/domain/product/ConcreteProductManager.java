package edu.luc.comp433.domain.product;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcreteProductManager implements ProductManager {

  ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  
  public ConcreteProductManager() {}

    @Override
    public boolean addProduct(String name, String desc) {
      Product product = (Product) context.getBean("product");
      product.setName(name);
      product.setDesc(desc);
      if (product.getName() == name) {
        return true;
      }
      else {
        return false;
      }
    }

    @Override
    public boolean updateProduct(String desc, double id) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean updateProduct(double cost, double id) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean updateProduct(long stock, double id) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean removeProduct(double id) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public Product getProduct(String name, String userName) {
      // TODO Auto-generated method stub
      return null;
    }

}
