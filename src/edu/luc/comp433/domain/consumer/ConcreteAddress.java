package edu.luc.comp433.domain.consumer;

public class ConcreteAddress implements Address {

  private String type;
  private String address;
  
  public ConcreteAddress() {}
  
  @Override
  public void setAddressType(String type) {
    this.type = type;
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String getAddressType() {
    return type;
  }

  @Override
  public String getAddress() {
    return address;
  }

}
