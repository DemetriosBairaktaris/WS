package edu.luc.comp433.domain.consumer;

import java.util.List;

public interface Consumer {
  public void setUserName(String userName);
  public String getUserName();
  public void setFirstName(String firstName);
  public String getFirstName();
  public void setLastName(String lastName);
  public String getLastName();
  public void setAddresses(List<Address> addresses);
  public List<Address> getAddresses();
  public void setPhones(List<Phone> phones);
  public List<Phone> getPhones();
  public void setPayments(List<Payment> payments);
  public List<Payment> getPayments();
}
