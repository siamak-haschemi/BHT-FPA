package de.bht.fpa.factorymethod.v5;

/**
 * This class has following responsibilities:
 * <ul>
 * <li>forwards the creation of an {@link Mammal} instance to a concrete creator
 * ({@link HorseCreator} or {@link PersonCreator})
 * <li>initializes the created {@link Mammal}s</li>
 * <li>calculates the age the created {@link Mammal}s</li>
 * <li>prints the description of the created {@link Mammal}s</li>
 * </ul>
 * 
 * @author benjaminhaupt & siamakhaschemi
 */
public abstract class MammalCreator {

  protected abstract Mammal createMammal(String gender, String name);

  public final Mammal getMammal(String xmlGender, String xmlName) {
    Mammal mammal = createMammal(xmlGender, xmlName);
    mammal.initialize();
    mammal.calculateAge();
    return mammal;
  }
}
