package edu.luc.comp433.domain.partner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcretePartnerManager implements PartnerManager {
    
  ApplicationContext context = 
    new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  
    public ConcretePartnerManager() {}
    
    @Override
    public boolean register(String name) {
      PartnerProfile profile = (PartnerProfile) context.getBean("partner");
      //TODO get ID from database key
      profile.setId(0);
      profile.setName(name);
      //TODO check ID here as well
      if (profile.getName() == name) {
        return true;
      }
      else {
        return false;
      }
    }
    
    @Override
    public boolean delete(double id) {
      //TODO use ID to call table deletion method in DAO here
      return false;
    }
}
