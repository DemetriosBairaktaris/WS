package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Product representation to help parse XML and JSON requests.
 * 
 * @author Demetrios and Thaddeus
 *
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRequest {
  private String desc;
  private float cost;
  private long stock;
  private String companyUserName;
  private String name;

  public ProductRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }

  public String getCompanyUserName() {
    return companyUserName;
  }

  public void setCompanyUserName(String companyUserName) {
    this.companyUserName = companyUserName;
  }

  @Override
  public String toString() {
    String output = "{name: %s,\n desc: %s,\n companyUserName: %s,\n stock: %d,\n cost: %f }";
    output = String.format(output, this.getName(), this.getDesc(), this.getCompanyUserName(), this.getStock(),
        this.getCost());
    return output;
  }

}
