package edu.luc.comp433.domain.consumer;

public class ConcretePhone implements Phone {

  private String number;
  private String type;
  
  public ConcretePhone() {}
  
  @Override
  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String getNumber() {
    return number;
  }

  @Override
  public String getType() {
    return type;
  }
}
