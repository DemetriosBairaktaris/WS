package edu.luc.comp433.domain.product;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;

public class ConcreteProductManager implements ProductManager {

  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  public ConcreteProductManager() {}

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }
  
  @Override
  public boolean addProduct(String name, String desc, double cost, String company, long stock) {
    Product product = (Product) context.getBean("product");
    product.setName(name);
    product.setDesc(desc);
    product.setCost(cost);
    product.setCompany(company);
    product.setStock(stock);
    if (database.insertProduct(product)) {
      return true;
    }
    else {
      return false;
    }
  }

  //TODO uncomment when database ready
  @Override
  public boolean updateDescription(String desc, String name) {
    //Product product = database.getProduct(name);
    //product.setDescription(desc);
    /**if (database.updateProduct(product)) {
      return true;
    } else {
      return false;
    }**/
    return false;
  }

  //TODO uncomment when database ready
  @Override
  public boolean updateCost(double cost, String name) {
    //Product product = database.getProduct(name);
    //product.setCost(cost);
    /**if (database.updateProduct(product)) {
      return true;
    } else {
      return false;
    }**/
    return false;
  }

  //TODO uncomment when database ready
    @Override
    public boolean updateStock(long stock, String name) {
      //Product product = database.getProduct(name);
      //product.setStock(stock);
      /**if (database.updateProduct(product)) {
        return true;
      } else {
        return false;
      }**/
      return false;
    }

    @Override
    public boolean removeProduct(String name) {
      //Product product = database.getProduct(name);
      /**if (database.deleteProduct(name)) {
        return true;
      } else {
        return false;
      }**/
      return false;
    }

    @Override
    public Product getProduct(String name) {
      //return database.getProduct(name);
      return null;
    }

}
