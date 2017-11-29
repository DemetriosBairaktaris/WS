package edu.luc.comp433.domain.partner;

public class ConcretePartnerProfile implements PartnerProfile {

  private String userName;
  private String name;
  private String address;
  private String phone;
  private String password;

  public ConcretePartnerProfile() {
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPhone() {
    return phone;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;

  }

  @Override
  public String getPassword() {
    return this.password;
  }
}
