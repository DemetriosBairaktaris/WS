package edu.luc.comp433.domain.product;

import java.sql.SQLException;
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

  @Override
  public boolean addProduct(String name, String desc, double cost, long stock, String companyName)
      throws SQLException {
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

  @Override
  public boolean deleteProduct(String companyName, String name) throws SQLException {
    List<Product> products = database.getProduct(name);
    Product product = (Product) context.getBean("product");
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getCompanyName().equals(companyName)
          && products.get(i).getName().equals(name)) {
        product = products.get(i);
      } else {
        product = null;
      }
    }
    return database.deleteProduct(product);
  }

  @Override
  public boolean updateStock(String companyName, String name, long stock) throws SQLException {
    List<Product> products = database.getProduct(name);
    Product product = (Product) context.getBean("product");
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getCompanyName().equals(companyName)
          && products.get(i).getName().equals(name)) {
        product = products.get(i);
        product.setStock(stock);
      } else {
        product = null;
      }
    }
    return database.updateProduct(product);
  }

  @Override
  public boolean updateCost(String companyName, String name, double cost) throws SQLException {
    List<Product> products = database.getProduct(name);
    Product product = (Product) context.getBean("product");
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getCompanyName().equals(companyName)
          && products.get(i).getName().equals(name)) {
        product = products.get(i);
        product.setCost(cost);
      } else {
        product = null;
      }
    }
    return database.updateProduct(product);
  }

  @Override
  public List<Product> getProducts(String name) throws SQLException {
    return database.getProduct(name);
  }

  @Override
  public boolean addReview(String companyName, String name, Review review) throws SQLException {
    List<Product> products = database.getProduct(name);
    Product product = (Product) context.getBean("product");
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getCompanyName().equals(companyName)
          && products.get(i).getName().equals(name)) {
        product = products.get(i);
        List<Review> reviews = product.getReviews();
        reviews.add(review);
      } else {
        product = null;
      }
    }
    return database.updateProduct(product);
  }

  @Override
  public List<Review> getReviews(String companyName, String name) throws SQLException {
    List<Product> products = database.getProduct(name);
    Product product = (Product) context.getBean("product");
    List<Review> reviews = new ArrayList<>();
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getCompanyName().equals(companyName)
          && products.get(i).getName().equals(name)) {
        product = products.get(i);
        reviews = product.getReviews();
      } else {
        product = null;
      }
    }
    return reviews;
  }

  @Override
  public List<Product> getCompanyProducts(String companyName) {
    List<Product> products = new ArrayList<>();
    // TODO figure out this logic
    return products;
  }

}
