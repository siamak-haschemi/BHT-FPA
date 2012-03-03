package de.bht.fpa.factorymethod.v5;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>creates the {@link MammalCreator} instance ({@link PersonCreator} or
 * {@link HorseCreator}) based on kind</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class Configurator {
  public MammalCreator getCreator(String kind) {
    if (kind.equals("person")) {
      return new PersonCreator();
    } else if (kind.equals("horse")) {
      return new PersonCreator();
    }
    throw new IllegalArgumentException("unknown kind type:" + kind);
  }
}
