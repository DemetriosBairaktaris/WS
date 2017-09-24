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
  public boolean addProduct(String name, String desc, double cost, long stock, String companyUserName)
      throws SQLException {
    Product product = (Product) context.getBean("product");
    List<Review> reviews = new ArrayList<>();
    product.setName(name);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);
    product.setCompanyUserName(companyUserName);
    product.setReviews(reviews);
    return database.insertProduct(product);
  }

  @Override
  public boolean deleteProduct(String companyUserName, String name) throws Exception {
	  return database.deleteProduct(database.getProductFromPartner(name, database.getPartnerProfile(companyUserName)));
  }

  @Override
  public boolean updateStock(String companyUserName, String name, long stock) throws Exception {
	  Product product = database.getProductFromPartner(name, database.getPartnerProfile(companyUserName)) ;
	  product.setStock(stock);
	  return database.updateProduct(product);
  }

  @Override
  public boolean updateCost(String companyUserName, String name, double cost) throws Exception {
	Product product = database.getProductFromPartner(name, database.getPartnerProfile(companyUserName)) ;
	product.setCost(cost);
    return database.updateProduct(product);
  }

  @Override
  public List<Product> getProducts(String name) throws Exception {
    return database.getProduct(name);
  }
  
  @Override
  public Product getProductFromPartner(String name, String partnerUserName) throws SQLException, Exception {
	  return database.getProductFromPartner(name, database.getPartnerProfile(partnerUserName));
  }

  @Override
  public boolean addReview(String companyUserName, String name, Review review) throws Exception {
	Product product = database.getProductFromPartner(name, database.getPartnerProfile(companyUserName)) ;
    List<Review> reviews = product.getReviews();
    reviews.add(review);
    return database.updateProduct(product);
  }

  @Override
  public List<Review> getReviews(String companyUserName, String name) throws Exception {
	Product product = database.getProductFromPartner(name, database.getPartnerProfile(companyUserName)) ;
	List<Review> reviews = product.getReviews();
    return reviews;
  }

  @Override
  public List<Product> getCompanyProducts(String companyUserName) throws SQLException, Exception {
    List<Product> products = new ArrayList<>();
    database.getAllProductsFromPartner(companyUserName);
    return products;
  }

}
