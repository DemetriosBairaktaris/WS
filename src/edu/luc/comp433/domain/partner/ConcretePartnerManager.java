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
    public boolean register(String name) {
      PartnerProfile profile = (PartnerProfile) context.getBean("partner");
      profile.setId(0);
      profile.setName(name);
      try {
        if((database.insertPartner(profile)) && (profile.getId() > 0)) {
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
    public boolean delete(String name) {
      //TODO use ID to call table deletion method in DAO here
      try {
        PartnerProfile profile = database.getPartnerProfile(name);
       
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
}
