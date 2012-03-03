package de.bht.fpa.factorymethod.v4;

import de.bht.fpa.factorymethod.v2.Horse;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>makes a distinction of gender</li>
 * <li>creates the {@link Horse}</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class HorseCreator extends MammalCreator {

  @Override
  protected Mammal createMammal(String gender, String name) {
    if (gender.equals("f")) {
      return new Mare(name);
      // process the children to build tree structure
    } else if (gender.equals("m")) {
      return new Stallion(name);
    }
    throw new IllegalArgumentException("unknown gender type:" + gender);
  }

}
