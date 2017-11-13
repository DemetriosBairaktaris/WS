/**
 * 
 */
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

  /**
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * @return the rel
   */
  public String getRel() {
    return rel;
  }

  /**
   * @param rel
   *          the rel to set
   */
  public void setRel(String rel) {
    this.rel = rel;
  }

  /**
   * @return the contentType
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * @param contentType
   *          the contentType to set
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * @return the action
   */
  public String getAction() {
    return action;
  }

  /**
   * @param action
   *          the action to set
   */
  public void setAction(String action) {
    this.action = action;
  }

}
