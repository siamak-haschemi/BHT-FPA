package de.bht.fpa.factorymethod.v2;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>makes a distinction of gender</li>
 * <li>creates the {@link Horse} and {@link Person} instances</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public class HorseAndPersonCreator {
  public Person getPerson(String gender, String name) {
    if (gender.equals("f")) {
      return new Women(name);
      // process the children to build tree structure
    } else if (gender.equals("m")) {
      return new Man(name);
    }
    throw new IllegalArgumentException("unknown gender type:" + gender);
  }

  public Horse getHorse(String gender, String name) {
    if (gender.equals("f")) {
      return new Mare(name);
      // process the children to build tree structure
    } else if (gender.equals("m")) {
      return new Stallion(name);
    }
    throw new IllegalArgumentException("unknown gender type:" + gender);
  }
}
