package de.bht.fpa.factorymethod.v4;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>makes a distinction of gender</li>
 * <li>creates the {@link Person}</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class PersonCreator extends MammalCreator {

  @Override
  protected Mammal createMammal(String gender, String name) {
    if (gender.equals("f")) {
      return new Women(name);
      // process the children to build tree structure
    } else if (gender.equals("m")) {
      return new Man(name);
    }
    throw new IllegalArgumentException("unknown gender type:" + gender);
  }

}
