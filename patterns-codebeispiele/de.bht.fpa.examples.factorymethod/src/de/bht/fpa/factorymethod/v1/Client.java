package de.bht.fpa.factorymethod.v1;

import de.bht.fpa.factorymethod.XMLElement;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>makes a distinction between kind ({@link Horse}, {@link Person}) and
 * gender</li>
 * <li>creates the {@link Women}, {@link Man}, {@link Mare}, and
 * {@link Stallion} instances</li>
 * <li>initializes the created {@link Horse}s and {@link Person}s</li>
 * <li>calculates the age the created {@link Horse}s and {@link Person}s</li>
 * <li>prints the description of the created {@link Horse}s and {@link Person}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Client {

  public void parseXML(XMLElement[] xmlElements, String kind) {
    for (XMLElement xmlElement : xmlElements) {
      String xmlGender = xmlElement.getGender();
      String xmlName = xmlElement.getName();

      if (kind.equals("horse")) {
        Horse horse = null;
        if (xmlGender.equals("f")) {
          horse = new Mare(xmlName);
          // process the children to build tree structure
        } else if (xmlGender.equals("m")) {
          horse = new Stallion(xmlName);
        } else {
          throw new IllegalArgumentException("unknown gender type:" + xmlGender);
        }
        horse.initialize();
        horse.calculateAge();
        horse.printDescription();
      } else if (kind.equals("person")) {
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
      } else {
        throw new IllegalArgumentException("unknown kind type:" + kind);
      }
    }
  }
}
