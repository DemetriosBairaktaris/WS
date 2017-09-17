package edu.luc.comp433.domain.consumer;

public class ConcreteConsumer implements Consumer {

  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private Payment payment;
  
  
  public ConcreteConsumer() {};
  
  @Override
  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String getUserName() {
    return userName;
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

  @Override
  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  @Override
  public Payment getPayment() {
    return payment;
  }
}
