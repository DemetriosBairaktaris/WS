package edu.luc.comp433.domain.consumer;

public interface Consumer {
  public void setID(int id);
  public int getID();
  public void setFirstName(String firstName);
  public String getFirstName();
  public void setLastName(String lastName);
  public String getLastName();
  public void setAddress(Address address);
  public Address getAddress(String type);
  public void setPhone(Phone phone);
  public Phone getPhone(String type);
}
