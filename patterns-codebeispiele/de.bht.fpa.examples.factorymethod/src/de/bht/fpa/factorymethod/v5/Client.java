package de.bht.fpa.factorymethod.v5;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>parses the {@link XMLElement}</li>
 * <li>forwards the creation of the {@link MammalCreator} to the
 * {@link Configurator}</li>
 * <li>forwards distinction of kind, gender, and creation of instances to
 * {@link MammalCreator}</li>
 * <li>prints the description of the created {@link Mammal}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Client {
  public void parseXML(XMLElement[] xmlElements, String kind) {
    MammalCreator creator = new Configurator().getCreator(kind);
    parseXMLWithCreator(xmlElements, creator);
  }

  private void parseXMLWithCreator(XMLElement[] xmlElements, MammalCreator creator) {
    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      Mammal mammal = creator.getMammal(xmlGender, xmlName);
      mammal.printDescription();
    }
  }
}
