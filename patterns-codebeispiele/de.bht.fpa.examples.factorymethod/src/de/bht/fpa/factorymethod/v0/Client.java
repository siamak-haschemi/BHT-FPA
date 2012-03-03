package de.bht.fpa.factorymethod.v0;

import javax.xml.bind.annotation.XmlElements;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>parses the {@link XmlElements}</li>
 * <li>makes a distinction between gender of persons</li>
 * <li>creates the {@link Women} and {@link Man} instances</li>
 * <li>initializes the created {@link Person}s</li>
 * <li>calculates the age the created {@link Person}s</li>
 * <li>prints the description of the created {@link Person}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Client {
  public void parseXML(XMLElement[] xmlElements) {
    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      Person person = null;
      if (xmlGender.equals("f")) {
        person = new Women(xmlName);
        // process the children to build tree structure
      } else if (xmlGender.equals("m")) {
        person = new Man(xmlName);
      } else {
        throw new IllegalArgumentException("unknown gender type:" + xmlGender);
      }
      person.initialize();
      person.calculateAge();
      person.printDescription();
    }
  }
}
