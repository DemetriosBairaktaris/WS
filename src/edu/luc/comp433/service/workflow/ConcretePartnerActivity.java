package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRepresentation;

public class ConcretePartnerActivity implements PartnerActivity {

  private ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/app-context.xml");
  private PartnerManager partners;

  public ConcretePartnerActivity() {
  }

  @Override
  public PartnerManager getPartners() {
    return partners;
  }

  @Override
  public void setPartners(PartnerManager partners) {
    this.partners = partners;
  }

  @Override
  public boolean addPartner(String userName, String companyName, String address, String phone)
      throws SQLException, Exception {
    return partners.createPartner(userName, companyName, address, phone);
  }

  @Override
  public boolean deletePartner(String userName) throws SQLException, Exception {
    return partners.deletePartner(userName);
  }

  @Override
  public boolean updatePartnerName(String userName, String companyName) throws SQLException, Exception {
    return partners.updateName(userName, companyName);
  }

  @Override
  public boolean updatePartnerAddress(String userName, String address) throws SQLException, Exception {
    return partners.updateAddress(userName, address);
  }

  @Override
  public boolean updatePartnerPhone(String userName, String phone) throws SQLException, Exception {
    return partners.updatePhone(userName, phone);
  }

  @Override
  public PartnerRepresentation getPartnerByUserName(String userName) throws SQLException, Exception {
    PartnerProfile partner = partners.getPartnerProfile(userName);
    PartnerRepresentation representation = this.assemblePartnerToRepresentation(partner);
    return representation;
  }

  @Override
  public PartnerRepresentation assemblePartnerToRepresentation(PartnerProfile partner) {
    PartnerRepresentation representation = (PartnerRepresentation) context.getBean("PartnerRepresentation");
    String userName = partner.getUserName();
    String companyName = partner.getName();
    String phone = partner.getPhone();
    String address = partner.getAddress();
    representation.setUserName(userName);
    representation.setName(companyName);
    representation.setPhone(phone);
    representation.setAddress(address);
    return representation;
  }

  @Override
  public String getPartnerSales(String userName) throws Exception {
    List<String> partnerOrders = new LinkedList<>();
    for (int i = 0; i < partners.getOrdersFromPartner(userName).size(); i++) {
      for (int j = 0; i < partners.getOrdersFromPartner(userName).get(i).getDetails().size(); j++) {
        if (partners.getOrdersFromPartner(userName).get(i).getDetails().get(j).getCompany().equals(userName)) {
          partnerOrders.add(partners.getOrdersFromPartner(userName).get(i).getDetails().get(j).toString());
        }
      }
    }
    return partnerOrders.toString();
  }

  @Override
  public List<OrderRepresentation> getOrdersFromPartner(String partnerUserName) {
    List<OrderRepresentation> representations = new LinkedList<>();
    List<Order> orders = Arrays.asList();
    try {
      orders = partners.getOrdersFromPartner(partnerUserName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (Order order : orders) {
      representations.add(this.assembleOrderToRepresentation(order));
    }

    return representations;
  }

  @Override
  public OrderRepresentation assembleOrderToRepresentation(Order order) {
    OrderRepresentation representation = (OrderRepresentation) context.getBean("orderRepresentation");
    if (order == null) {
      // if order wasn't found set id to one and the above layer will check
      representation.setOrderId(-1);
    } else {
      representation.setCustomer(order.getCustomer());
      representation.setOrderId(order.getOrderId());
      representation.setStatus(order.getStatus());
      representation.setTimestamp(order.getTimestamp().toString());
    }
    return representation;
  }

}
