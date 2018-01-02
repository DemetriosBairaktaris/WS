package edu.luc.comp433.domain.partner;

import edu.luc.comp433.dal.DatabaseAccess;
import edu.luc.comp433.domain.order.Order;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcretePartnerManager implements PartnerManager {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/app-context.xml");
  private DatabaseAccess database;

  public ConcretePartnerManager() {
  }

  @Override
  public boolean createPartner(String userName, String name, String address, String phone, String password)
      throws SQLException, Exception {
    PartnerProfile profile = (PartnerProfile) context.getBean("partner");
    profile.setUserName(userName);
    profile.setName(name);
    profile.setAddress(address);
    profile.setPhone(phone);
    profile.setPassword(password);
    return database.insertPartner(profile);
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException, Exception {
    return database.deletePartner(userName);
  }

  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }

  @Override
  public PartnerProfile getPartnerProfile(String userName) throws SQLException, Exception {
    PartnerProfile profile = database.getPartnerProfile(userName);
    return profile;
  }

  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }

  @Override
  public boolean updateAddress(String userName, String address) throws SQLException, Exception {
    PartnerProfile profile = database.getPartnerProfile(userName);
    profile.setAddress(address);
    return database.updatePartner(profile);
  }

  @Override
  public boolean updateName(String userName, String name) throws SQLException, Exception {
    PartnerProfile profile = database.getPartnerProfile(userName);
    profile.setName(name);
    return database.updatePartner(profile);
  }

  @Override
  public boolean updatePhone(String userName, String phone) throws SQLException, Exception {
    PartnerProfile profile = database.getPartnerProfile(userName);
    profile.setPhone(phone);
    return database.updatePartner(profile);
  }

  @Override
  public List<Order> getOrdersFromPartner(String userName) throws Exception {
    return database.getOrdersFromPartner(userName);
  }

}
