package edu.luc.comp433.domain.consumer;

public interface ConsumerManager {
  public boolean createConsumer(String firstName, String lastName);
  public boolean updateConsumer(Address address, int id);
  public boolean updateConsumer(Phone phone, int id);
  public boolean updateConsumer(Payment payment, int id);
  public boolean deleteConsumer(int id);
}
