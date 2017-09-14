package edu.luc.comp433.domain.partner;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;

public class ConcretePartnerManager implements PartnerManager {
    
  ApplicationContext context = 
    new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database = (DatabaseAccess) context.getBean("dao");
  
    public ConcretePartnerManager() {}
    
    @Override
    public boolean register(String name) {
      PartnerProfile profile = (PartnerProfile) context.getBean("partner");
      //TODO get ID from database key
      profile.setId(0);
      profile.setName(name);
      try {
        database.insertPartner(profile);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      //TODO check ID here as well
      if (profile.getName() == name) {
        return true;
      }
      else {
        return false;
      }
    }
    
    @Override
    public boolean delete(String name) {
      //TODO use ID to call table deletion method in DAO here
      try {
        PartnerProfile profile = database.getPartnerProfile(name);
        database.deletePartner(profile);
        if (database.getPartnerProfile(name) == null) {
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
