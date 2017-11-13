/**
 * 
 */
package edu.luc.comp433.service.representation;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * This is for generating links.
 * 
 * @author Thaddeus and Demetrios
 *
 */
public abstract class AbstractRepresentation {

  private List<ProtocolLink> protocols;

  public AbstractRepresentation() {
    protocols = new LinkedList<>();
  }

  /**
   * @return the protocols
   */
  @XmlElement(name = "link", namespace = "")
  public List<ProtocolLink> getProtocols() {
    return protocols;
  }

  /**
   * @param protocols
   *          the protocols to set
   */
  public void setProtocols(List<ProtocolLink> protocols) {
    this.protocols = protocols;
  }

  /**
   * Adds a link to the list.
   * 
   * @param link
   *          ProtocolLink
   */
  public void addLink(ProtocolLink link) {
    protocols.add(link);
  }

  /**
   * Removes a link from the list.
   * 
   * @param link
   *          ProtocolLink
   */
  public void removeLink(ProtocolLink link) {
    protocols.remove(link);
  }

}
