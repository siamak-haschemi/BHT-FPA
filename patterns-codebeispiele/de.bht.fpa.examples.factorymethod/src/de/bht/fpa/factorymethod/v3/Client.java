package de.bht.fpa.factorymethod.v3;

import javax.xml.bind.annotation.XmlElements;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>parses the {@link XmlElements}</li>
 * <li>creates the {@link MammalCreator} instance</li>
 * <li>forwards distinction of kind, gender, and creation of instances to
 * {@link MammalCreator}</li>
 * <li>initializes the created {@link Mammal}s</li>
 * <li>calculates the age the created {@link Mammal}s</li>
 * <li>prints the description of the created {@link Mammal}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Client {
  public void parseXML(XMLElement[] xmlElements, String kind) {
    MammalCreator creator = new MammalCreator();

    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      Mammal mammal = creator.getMammal(kind, xmlGender, xmlName);
      mammal.initialize();
      mammal.calculateAge();
      mammal.printDescription();
    }
  }
}
