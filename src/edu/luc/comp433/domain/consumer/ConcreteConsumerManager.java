package edu.luc.comp433.domain.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcreteConsumerManager implements ConsumerManager {

  ApplicationContext context = 
      new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  
  public ConcreteConsumerManager() {}
  
  @Override
  public boolean createConsumer(String userName, String firstName, String lastName) {
    //TODO add method calls to persist consumer in DAL
    Consumer consumer = (Consumer) context.getBean("consumer");
    consumer.setFirstName(firstName);
    consumer.setLastName(lastName);
    if (consumer.getFirstName() == firstName) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public boolean updateConsumer(Address address, int id) {
    //TODO call to DAL to retrieve consumer object using ID here
    return false;
  }
  

  @Override
  public boolean updateConsumer(Payment payment, int id) {
    // TODO call to DAL to retrieve consumer object using ID here
    return false;
  }

  @Override
  public boolean updateConsumer(Phone phone, int id) {
    // TODO call to DAL to retrieve consumer object using ID here
    return false;
  }

  @Override
  public boolean deleteConsumer(String userName) {
    // TODO call to DAL to drop the consumer object using the ID here
    return false;
  }
}
