package de.bht.fpa.factorymethod.v5;

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
    } else if (gender.equals("m")) {
      return new Stallion(name);
    }
    throw new IllegalArgumentException("unknown gender type:" + gender);
  }

}
