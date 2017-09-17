package edu.luc.comp433.domain.consumer;

public interface Consumer {
  public void setUserName(String userName);
  public String getUserName();
  public void setFirstName(String firstName);
  public String getFirstName();
  public void setLastName(String lastName);
  public String getLastName();
  public void setAddress(String address);
  public String getAddress();
  public void setPhone(String phone);
  public String getPhone();
  public void setPayment(Payment payment);
  public Payment getPayment();
}
