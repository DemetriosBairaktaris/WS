package edu.luc.comp433.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dal.DatabaseAccess;

public class ConcreteProductManager implements ProductManager {

  private ApplicationContext context = new ClassPathXmlApplicationContext(
      "/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  public ConcreteProductManager() {
  }

  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  public boolean addProduct(String name, String desc, double cost, long stock, String companyName) {
    Product product = (Product) context.getBean("product");
    List<Review> reviews = new ArrayList<>();
    product.setName(name);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);
    product.setCompanyName(companyName);
    product.setReviews(reviews);
    return database.insertProduct(product);
  }
  
  public boolean deleteProduct(String name) {
    return database.deleteProduct(name);
  }
  
  public boolean updateStock(String name, long stock) {
    Product product = database.getProduct(name);
    product.setStock(stock);
    return database.updateProduct(product);
  }
  
  public boolean updateCost(String name, double cost) {
    Product product = database.getProduct(name);
    product.setCost(cost);
    return database.updateProduct(product);
  }
  
  public Product getProduct(String name) {
    return database.getProduct(name);
  }
  
  public boolean addReview(String name, Review review) {
    Product product = database.getProduct(name);
    List<Review> reviews = product.getReviews();
    reviews.add(review);
    return database.updateProduct(product);
  }
  
  public List<Review> getReviews(String name) {
    return database.getProduct(name).getReviews();
  }
  
  public List<Product> getCompanyProducts(String companyName) {
    List<Product> products = new ArrayList<>();
    //TODO figure out this logic
    return products;
  }
  
}
