package edu.luc.comp433.service;

import java.util.Set;

import javax.jws.WebService;

import edu.luc.comp433.service.representation.ProductRepresentation;
import edu.luc.comp433.service.representation.ProductRequest;

@WebService
public interface ProductService {

  public Set<ProductRepresentation> getProduct(String productName);

  public ProductRepresentation insertProduct(ProductRequest request);

}
