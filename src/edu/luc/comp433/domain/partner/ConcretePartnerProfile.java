package edu.luc.comp433.domain.partner;

public class ConcretePartnerProfile implements PartnerProfile {

  private String userName;
  private String name;
  private String address;
  private String phone;

  public ConcretePartnerProfile() {}

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setUserName(String userName) {
    this.userName = userName;
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
    public String getAddress() {
      return address;
    }

    @Override
    public void setPhone(String phone) {
      this.phone = phone;
    }

    @Override
    public String getPhone() {
      return phone;
    }
}
