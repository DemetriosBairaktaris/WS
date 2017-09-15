package edu.luc.comp433.domain.partner;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;

public class ConcretePartnerManager implements PartnerManager {
    
  private ApplicationContext context = 
    new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
    public ConcretePartnerManager() {}
    
    public void setDatabase(DatabaseAccess database) {
      this.database = database;
    }
    
    public DatabaseAccess getDatabase() {
      return database;
    }
    
    @Override
    public boolean create(String userName, String name, String address, String phone) {
      PartnerProfile profile = (PartnerProfile) context.getBean("partner");
      profile.setUserName(userName);
      profile.setName(name);
      profile.setAddress(address);
      profile.setPhone(phone);
      try {
        if((database.insertPartner(profile))) {
        		return true ; 
        }
        else {
          return false;
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
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
        }
        else {
          return false;
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
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
        }
        else {
          return false;
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return false;
    }

    @Override
    public boolean updateAddress(String userName, String address) {
      try {
        PartnerProfile profile = database.getPartnerProfile(userName);
        profile.setAddress(address);
        if (database.updatePartner(profile)) {
          return true;
        }
        else {
          return false;
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return false;
    }

    @Override
    public boolean updatePhone(String userName, String phone) {
      try {
        PartnerProfile profile = database.getPartnerProfile(userName);
        profile.setPhone(phone);
        if (database.updatePartner(profile)) {
          return true;
        }
        else {
          return false;
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return false;
    }
}
