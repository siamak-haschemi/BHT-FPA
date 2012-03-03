package de.bht.fpa.factorymethod.v4;

import javax.xml.bind.annotation.XmlElements;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>parses the {@link XmlElements}</li>
 * <li>creates the {@link MammalCreator} instance ({@link PersonCreator} or
 * {@link HorseCreator})</li>
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
    MammalCreator creator = null;
    if (kind.equals("person")) {
      creator = new PersonCreator();
    } else if (kind.equals("horse")) {
      creator = new HorseCreator();
    } else {
      throw new IllegalArgumentException("unknown kind type:" + kind);
    }

    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      Mammal mammal = creator.createMammal(xmlGender, xmlName);
      mammal.initialize();
      mammal.calculateAge();
      mammal.printDescription();
    }
  }
}
