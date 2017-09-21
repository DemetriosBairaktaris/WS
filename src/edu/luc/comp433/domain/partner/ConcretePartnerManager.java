package edu.luc.comp433.domain.partner;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.product.Product;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcretePartnerManager implements PartnerManager {

  private ApplicationContext context = new ClassPathXmlApplicationContext(
      "/WEB-INF/app-context.xml");
  private DatabaseAccess database;

  public ConcretePartnerManager() {
  }

  @Override
  public boolean addProduct(String userName, String name, String desc, double cost, long stock)
      throws Exception {
    Product product = (Product) context.getBean("product");
    product.setName(name);
    product.setDesc(desc);
    product.setCost(cost);
    product.setStock(stock);
    PartnerProfile partner = database.getPartnerProfile(userName);
    partner.addProduct(product);
    if (database.updatePartner(partner)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean create(String userName, String name, String address, String phone) {
    PartnerProfile profile = (PartnerProfile) context.getBean("partner");
    profile.setUserName(userName);
    profile.setName(name);
    profile.setAddress(address);
    profile.setPhone(phone);
    try {
      if ((database.insertPartner(profile))) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean delete(String userName) {
    try {
      PartnerProfile profile = database.getPartnerProfile(userName);
      if (database.deletePartner(profile)) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }

  @Override
  public List<Order> getOrders(String userName) throws Exception {
    return database.getPartnerProfile(userName).getOrders();
  }

  @Override
  public PartnerProfile getPartnerProfile(String userName) {
    try {
      PartnerProfile profile = database.getPartnerProfile(userName);
      return profile;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Product getProduct(String name, String partnerUserName) throws SQLException {
    return database.getProducts(name,this.getPartnerProfile(partnerUserName)).get(0); // TODO implement this method in the DAL
  }

  @Override
  public boolean removeProduct(String userName, String name) throws Exception {
	  
    PartnerProfile partner = database.getPartnerProfile(userName);
    Product product = (Product) context.getBean("product");
    product.setName(name);
    return database.deleteProduct(product, partner);
    
  }

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }

  @Override
  public boolean updateAddress(String userName, String address) {
    try {
      PartnerProfile profile = database.getPartnerProfile(userName);
      profile.setAddress(address);
      if (database.updatePartner(profile)) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean updateCost(String userName, double cost, String name)
      throws SQLException, Exception {
    PartnerProfile partner = database.getPartnerProfile(userName);
    for (int i = 0; i < partner.getProducts().size(); i++) {
      if (partner.getProducts().get(i).getName().equals(name)) {
        partner.getProducts().get(i).setCost(cost);
        if (database.updatePartner(partner)) {
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public boolean updateDescription(String userName, String desc, String name)
      throws SQLException, Exception {
    PartnerProfile partner = database.getPartnerProfile(userName);
    for (int i = 0; i < partner.getProducts().size(); i++) {
      if (partner.getProducts().get(i).getName().equals(name)) {
        partner.getProducts().get(i).setDesc(desc);
        if (database.updatePartner(partner)) {
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public boolean updateName(String userName, String name) {
    try {
      PartnerProfile profile = database.getPartnerProfile(userName);
      profile.setName(name);
      if (database.updatePartner(profile)) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean updateOrders(String userName, List<Order> orders) throws Exception {
    PartnerProfile partner = database.getPartnerProfile(userName);
    for (int i = 0; i < orders.size(); i++) {
      partner.addOrder(orders.get(i));
    }
    if (database.updatePartner(partner)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updatePhone(String userName, String phone) {
    try {
      PartnerProfile profile = database.getPartnerProfile(userName);
      profile.setPhone(phone);
      if (database.updatePartner(profile)) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean updateStock(String userName, long stock, String name)
      throws SQLException, Exception {
    PartnerProfile partner = database.getPartnerProfile(userName);
    for (int i = 0; i < partner.getProducts().size(); i++) {
      if (partner.getProducts().get(i).getName().equals(name)) {
        partner.getProducts().get(i).setStock(stock);
        if (database.updatePartner(partner)) {
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }
}
