package edu.luc.comp433.domain.consumer;

public interface ConsumerManager {
  public boolean createConsumer(String firstName, String lastName);
  public boolean updateConsumer(Address address);
  public boolean updateConsumer(Phone phone);
  public boolean deleteConsumer(int id);
}
