package edu.luc.comp433.service.workflow;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderManager;
import edu.luc.comp433.domain.partner.PartnerManager;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;
import edu.luc.comp433.domain.product.ProductManager;
import edu.luc.comp433.service.representation.OrderRepresentation;
import edu.luc.comp433.service.representation.PartnerRepresentation;
import edu.luc.comp433.service.representation.ProductRepresentation;

public class ConcreteDomainFacade implements DomainFacade {

  private PartnerManager partners;
  private OrderManager orders;
  private ProductManager products;

  public ConcreteDomainFacade() {
  }

  public PartnerManager getPartners() {
    return partners;
  }

  public void setPartners(PartnerManager partners) {
    this.partners = partners;
  }

  public OrderManager getOrders() {
    return orders;
  }

  public void setOrders(OrderManager orders) {
    this.orders = orders;
  }

  public ProductManager getProducts() {
    return products;
  }

  public void setProducts(ProductManager products) {
    this.products = products;
  }

  @Override
  public ProductRepresentation getProductFromPartner(String productName, String partnerUserName)
      throws SQLException, Exception {
    Product product = products.getProductFromPartner(productName, partnerUserName);
    ProductRepresentation representation;
    representation = this.assembleProductToRepresentation(product);
    return representation;
  }

  @Override
  public List<ProductRepresentation> searchProduct(String productName) throws Exception {
    List<Product> listOfFoundProducts = products.getProducts(productName);
    List<ProductRepresentation> representations = new LinkedList<>();
    for (Product product : listOfFoundProducts) {
      representations.add(this.assembleProductToRepresentation(product));
    }
    return representations;
  }

  @Override
  public boolean checkAvailability(String productName) throws Exception {
    long stock = 0;
    for (int i = 0; i < products.getProducts(productName).size(); i++) {
      stock = products.getProducts(productName).get(i).getStock();
      if (stock >= 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int buyProduct(String customerName, String productName, long quantity, int orderId) throws Exception {
    long newStock = 0;
    int success = -1;
    if (this.checkAvailability(productName)) {
      for (int i = 0; i < products.getProducts(productName).size(); i++) {
        if (products.getProducts(productName).get(i).getStock() >= quantity) {
          newStock = products.getProducts(productName).get(i).getStock() - quantity;
          String companyName = products.getProducts(productName).get(i).getCompanyUserName();
          products.updateStock(companyName, productName, newStock);
          int paymentResult = this.acceptPayment(companyName, customerName, productName, quantity, orderId);
          if (paymentResult < 1) {
            this.cancelOrder(orderId); // this should handle all the rollback if payment doesnt go through

            break;
          } else {
            success = paymentResult;
            break;
          }
        } else {
          this.cancelOrder(orderId);
          success = -1;
        }
      }
    }
    return success;
  }

  private int acceptPayment(String companyName, String customerName, String productName, long quantity, int orderId) {
    int result = -1;
    try {
      // removed to bypass need for customer activity temporarily
      // if
      // (customers.getCustomer(customerName).getPayment().getExpiration().compareTo(currentTime)
      // > 0) {
      result = this.createOrder(companyName, customerName, productName, quantity, orderId);
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private int createOrder(String companyName, String customerName, String productName, long quantity, int orderId)
      throws Exception {
    if (orderId == 0) {
      orderId = orders.createOrder(customerName);
    }
    for (int i = 0; i < products.getProducts(productName).size(); i++) {
      if (products.getProducts(productName).get(i).getName().equals(productName)
          && products.getProducts(productName).get(i).getCompanyUserName().equals(companyName)) {
        orders.createOrderDetail(orderId, products.getProducts(productName).get(i), quantity);
        return orderId;
      }
    }
    return -1;
  }

  @Override
  public OrderRepresentation getOrderById(int orderId) {
    OrderRepresentation representation;
    Order order = null;
    try {
      order = orders.getOrder(orderId);
    } catch (Exception e) {

    }
    representation = this.assembleOrderToRepresentation(order);
    return representation;
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
  public boolean fulfillOrder(int orderId) throws SQLException, Exception {
    return orders.fulfillOrder(orderId);
  }

  @Override
  public int cancelOrder(int orderId) throws SQLException, Exception {
    int limit = orders.getOrder(orderId).getDetails().size();
    int refund = 0;
    if (this.refund(orderId) > 0) {
      for (int i = 0; i < limit; i++) {
        long quantity = orders.getOrder(orderId).getDetails().get(i).getQuantity();
        String companyName = orders.getOrder(orderId).getDetails().get(i).getCompany();
        String name = orders.getOrder(orderId).getDetails().get(i).getProduct().getName();
        long stock = 0;
        for (int j = 0; j < products.getProducts(name).size(); j++) {
          if (products.getProducts(name).get(j).getCompanyUserName().equals(companyName)) {
            stock = quantity + products.getProducts(name).get(j).getStock();
          }
        }
        refund = this.refund(orderId);
        products.updateStock(companyName, name, stock);
      }
      orders.cancelOrder(orderId);
      return refund;
    } else {
      return -1;
    }
  }

  private int refund(int orderId) throws SQLException, Exception {
    int totalRefund = 0;
    for (int i = 0; i < orders.getOrder(orderId).getDetails().size(); i++) {
      totalRefund += orders.getOrder(orderId).getDetails().get(i).getProduct().getCost();
    }
    return totalRefund;
  }

  @Override
  public boolean shipOrder(int orderId) throws SQLException, Exception {
    return orders.shipOrder(orderId);
  }

  @Override
  public String getOrderStatus(int orderId) throws SQLException, Exception {
    return orders.getOrder(orderId).getStatus();
  }

  @Override
  public boolean addReview(String companyName, String productName, String desc, int rating)
      throws SQLException, Exception {
    return products.addReview(companyName, productName, desc, rating);
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
  public boolean acceptPartnerProduct(String userName, String productName, String productDesc, double cost, long stock)
      throws SQLException, Exception {
    return products.addProduct(productName, productDesc, cost, stock,
        partners.getPartnerProfile(userName).getUserName());
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
  public PartnerRepresentation getPartnerByUserName(String userName) throws SQLException, Exception {
    PartnerProfile partner = partners.getPartnerProfile(userName);
    PartnerRepresentation representation = this.assemblePartnerToRepresentation(partner);
    return representation;
  }

  /**
   * Method to assemble order from below
   * 
   * @param order
   * @return
   */
  private OrderRepresentation assembleOrderToRepresentation(Order order) {
    OrderRepresentation representation = new OrderRepresentation();
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

  /**
   * Method to assemble a product from below.
   * 
   * @param product
   *          Product to be assembled
   * @return completed ProductRepresentation
   */
  private ProductRepresentation assembleProductToRepresentation(Product product) {
    ProductRepresentation currentProduct = new ProductRepresentation();
    currentProduct.setName(product.getName());
    currentProduct.setCompanyUserName(product.getCompanyUserName());
    currentProduct.setCost((float) product.getCost());
    currentProduct.setDesc(product.getDesc());
    currentProduct.setStock(product.getStock());
    return currentProduct;
  }

  /**
   * 
   * @param partner
   * @return
   */
  private PartnerRepresentation assemblePartnerToRepresentation(PartnerProfile partner) {
    PartnerRepresentation representation = new PartnerRepresentation();
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
}
