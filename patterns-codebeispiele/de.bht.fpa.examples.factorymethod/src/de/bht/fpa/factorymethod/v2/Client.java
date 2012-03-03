package de.bht.fpa.factorymethod.v2;

import javax.xml.bind.annotation.XmlElements;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>parses the {@link XmlElements}</li>
 * <li>creates the {@link HorseAndPersonCreator} instance</li>
 * <li>makes a distinction between kind</li>
 * <li>forwards distinction of gender and creation of instances to
 * {@link HorseAndPersonCreator}</li>
 * <li>initializes the created {@link Horse}s and {@link Person}s</li>
 * <li>calculates the age the created {@link Horse}s and {@link Person}s</li>
 * <li>prints the description of the created {@link Horse}s and {@link Person}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Client {

  public void parseXML(XMLElement[] xmlElements, String kind) {
    HorseAndPersonCreator creator = new HorseAndPersonCreator();

    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      if (kind.equals("horse")) {
        Horse horse = creator.getHorse(xmlGender, xmlName);
        horse.initialize();
        horse.calculateAge();
        horse.printDescription();
      } else if (kind.equals("person")) {
        Person person = creator.getPerson(xmlGender, xmlName);
        person.initialize();
        person.calculateAge();
        person.printDescription();
      } else {
        throw new IllegalArgumentException("unknown kind type:" + kind);
      }
    }
  }
}
