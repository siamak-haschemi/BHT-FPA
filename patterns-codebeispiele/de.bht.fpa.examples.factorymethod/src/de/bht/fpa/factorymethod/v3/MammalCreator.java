package de.bht.fpa.factorymethod.v3;

import de.bht.fpa.factorymethod.v2.Horse;
import de.bht.fpa.factorymethod.v2.Person;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>makes a distinction of kind and gender</li>
 * <li>creates the {@link Horse} and {@link Person} instances</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class MammalCreator {

  public Mammal getMammal(String kind, String gender, String name) {
    if (kind.equals("person")) {
      if (gender.equals("f")) {
        return new Women(name);
        // process the children to build tree structure
      } else if (gender.equals("m")) {
        return new Man(name);
      }
      throw new IllegalArgumentException("unknown gender type:" + gender);
    } else if (kind.equals("horse")) {
      if (gender.equals("f")) {
        return new Mare(name);
        // process the children to build tree structure
      } else if (gender.equals("m")) {
        return new Stallion(name);
      }
      throw new IllegalArgumentException("unknown gender type:" + gender);
    }
    throw new IllegalArgumentException("unknown kind type:" + kind);
  }
}
