package edu.luc.comp433.domain.consumer;

//import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.dao.DatabaseAccess;

public class ConcreteConsumerManager implements ConsumerManager {

  private ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private DatabaseAccess database;
  
  public ConcreteConsumerManager() {}
  
  @Override
  public void setDatabase(DatabaseAccess database) {
    this.database = database;
  }
  
  @Override
  public DatabaseAccess getDatabase() {
    return database;
  }
  
  @Override
  public boolean create(String userName, String firstName, String lastName) {
    //TODO add method calls to persist consumer in DAL
    Consumer consumer = (Consumer) context.getBean("consumer");
    consumer.setUserName(userName);
    consumer.setFirstName(firstName);
    consumer.setLastName(lastName);
    /**try {
        if((database.insertConsumer(consumer))) {
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
      }**/
    return false;
  }

  @Override
  public boolean updateAddress(String userName, Address address) {
    //TODO call to DAL to retrieve consumer object using ID here
    /**try {
      Consumer consumer = database.getConsumer(userName);
      consumer.setUserName(userName);
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
    }**/
    return false;
  }
  

  @Override
  public boolean updatePayment(String userName, Payment payment) {
    // TODO call to DAL to retrieve consumer object using ID here
    return false;
  }

  @Override
  public boolean updatePhone(String userName, Phone phone) {
    // TODO call to DAL to retrieve consumer object using ID here
    return false;
  }

  @Override
  public boolean delete(String userName) {
    // TODO call to DAL to drop the consumer object using the ID here
    return false;
  }

  @Override
  public Consumer getConsumer(String userName) {
    /**try {
      Consumer consumer = database.getConsumer(userName);
      return consumer;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }**/
    return null;
  }
}
