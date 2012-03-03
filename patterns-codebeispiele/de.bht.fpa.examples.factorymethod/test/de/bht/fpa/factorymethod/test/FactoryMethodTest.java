package de.bht.fpa.factorymethod.test;

import static de.bht.fpa.factorymethod.test.XMLElementBuilder.newXMLElementBuilder;

import org.junit.Before;
import org.junit.Test;

import de.bht.fpa.factorymethod.XMLElement;

public class FactoryMethodTest {

  private XMLElement[] persons;
  private XMLElement[] horses;
  private XMLElement[] aliens;

  @Before
  public void setUp() {
    XMLElementBuilder female = newXMLElementBuilder().gender("f");
    XMLElementBuilder male = newXMLElementBuilder().gender("m");
    XMLElementBuilder alien = newXMLElementBuilder().gender("$§%");

    // @formatter:off
    persons = new XMLElement[] { 
        female.but().name("Klara").build(), 
        female.but().name("Eva").build(),
        male.but().name("Klaus").build() 
    };

    horses = new XMLElement[] { 
        female.but().name("Acromantula").build(), 
        female.but().name("Abu").build(),
        male.but().name("Samara").build() 
    };
    
    aliens = new XMLElement[] { 
        alien.but().name("32lka").build(), 
        alien.but().name("9kka2").build(),
        alien.but().name("21111sss").build() 
    };
    // @formatter:on
  }

  /**
   * All implementations can handle persons
   */
  @Test
  public void testPersons() {
    new de.bht.fpa.factorymethod.v0.Client().parseXML(persons);
    new de.bht.fpa.factorymethod.v1.Client().parseXML(persons, "person");
    new de.bht.fpa.factorymethod.v2.Client().parseXML(persons, "person");
    new de.bht.fpa.factorymethod.v3.Client().parseXML(persons, "person");
    new de.bht.fpa.factorymethod.v4.Client().parseXML(persons, "person");
    new de.bht.fpa.factorymethod.v5.Client().parseXML(persons, "person");
  }

  /**
   * All but the first implementation can handle horses
   */
  @Test
  public void testHorses() {
    new de.bht.fpa.factorymethod.v1.Client().parseXML(horses, "horse");
    new de.bht.fpa.factorymethod.v2.Client().parseXML(horses, "horse");
    new de.bht.fpa.factorymethod.v3.Client().parseXML(horses, "horse");
    new de.bht.fpa.factorymethod.v4.Client().parseXML(horses, "horse");
    new de.bht.fpa.factorymethod.v5.Client().parseXML(horses, "horse");
  }

  /**
   * No implementation can handle aliens
   */
  @Test(expected = IllegalArgumentException.class)
  public void testV1Aliens() {
    new de.bht.fpa.factorymethod.v1.Client().parseXML(aliens, "alien");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testV2Aliens() {
    new de.bht.fpa.factorymethod.v2.Client().parseXML(aliens, "alien");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testV3Aliens() {
    new de.bht.fpa.factorymethod.v3.Client().parseXML(aliens, "alien");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testV4Aliens() {
    new de.bht.fpa.factorymethod.v4.Client().parseXML(aliens, "alien");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testV5Aliens() {
    new de.bht.fpa.factorymethod.v5.Client().parseXML(aliens, "alien");
  }

}
