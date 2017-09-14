package edu.luc.comp433.domain.consumer;

import java.util.List;

public class ConcreteConsumer implements Consumer {

  private int id;
  private String firstName;
  private String lastName;
  private List<Address> addresses;
  private List<Phone> phones;
  private List<Payment> payments;
  
  
  public ConcreteConsumer() {};
  
  @Override
  public void setID(int id) {
    this.id = id;
  }

  @Override
  public int getID() {
    return id;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  @Override
  public List<Address> getAddresses() {
    return addresses;
  }

  @Override
  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  @Override
  public List<Phone> getPhones() {
    return phones;
  }

  @Override
  public void setPayments(List<Payment> payments) {
    this.payments = payments;
  }

  @Override
  public List<Payment> getPayments() {
    return payments;
  }
}
