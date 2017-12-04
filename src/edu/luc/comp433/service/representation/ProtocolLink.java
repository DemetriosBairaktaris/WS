package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is for protocol link generation to be returned to client.
 * 
 * @author Thaddeus and Demetrios
 *
 */
@XmlRootElement(name = "link")
public class ProtocolLink {

  private String uri;
  private String rel;
  private String contentType;
  private String action;

  public ProtocolLink() {
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

}
