package de.bht.fpa.statepattern;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class CoffeeMachineTest {
  private de.bht.fpa.statepattern.v1.CoffeeMachine machine = null;

  // de.bht.fpa.statepattern.v2.CoffeeMachine machine = null;
  // de.bht.fpa.statepattern.v3.CoffeeMachine machine = null;
  // de.bht.fpa.statepattern.v5.CoffeeMachine machine = null;

  @Before
  public void setUp() throws Exception {
    machine = new de.bht.fpa.statepattern.v1.CoffeeMachine();
    // machine = new de.bht.fpa.statepattern.v2.CoffeeMachine();
    // machine = new de.bht.fpa.statepattern.v3.CoffeeMachine();
    // machine = new de.bht.fpa.statepattern.v5.CoffeeMachine();
  }

  @Test
  public void testGoodCase() {
    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(selectBeverage(), is("Sie haben ein Getränk ausgewählt.Ihr Getränk wird vorbereitet."));
  }

  @Test
  public void testEjectChip() {
    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(ecjectChip(), is("Ihr eingelegter Chip wird ausgeworfen."));
  }

  @Test
  public void testBadCases() {
    assertThat(ecjectChip(), is("Sie haben keinen Chip eingelegt den wir Ihnen auswerfen können."));
    assertThat(selectBeverage(), is("Sie haben ein Getränk ausgewählt, aber kein Chip eingeworfen."));
  }

  @Test
  public void testSeveralChipInsertions() {
    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(insertChip(), is("Sie haben bereits einen Chip eingelegt."));
    assertThat(insertChip(), is("Sie haben bereits einen Chip eingelegt."));
  }

  private String ecjectChip() {
    String ecjectChip = machine.ecjectChip();
    System.out.println(ecjectChip);
    return ecjectChip;
  }

  private String selectBeverage() {
    String selectBeverage = machine.selectBeverage();
    System.out.println(selectBeverage);
    return selectBeverage;
  }

  private String insertChip() {
    String insertChip = machine.insertChip();
    System.out.println(insertChip);
    return insertChip;
  }
}
