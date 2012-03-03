package de.bht.fpa.statepattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NewCoffeeMachineTest {
  de.bht.fpa.statepattern.v4.CoffeeMachine machine = null;

  @Before
  public void setUp() throws Exception {
    machine = new de.bht.fpa.statepattern.v4.CoffeeMachine(3);
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

  @Test
  public void decrementsCapacity() {
    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(selectBeverage(), is("Sie haben ein Getränk ausgewählt.Ihr Getränk wird vorbereitet."));
    assertThat(machine.getAmountOfBeverage(), is(2));
  }

  @Test
  public void capacity() {
    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(selectBeverage(), is("Sie haben ein Getränk ausgewählt.Ihr Getränk wird vorbereitet."));
    assertThat(machine.getAmountOfBeverage(), is(2));

    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(selectBeverage(), is("Sie haben ein Getränk ausgewählt.Ihr Getränk wird vorbereitet."));
    assertThat(machine.getAmountOfBeverage(), is(1));

    assertThat(insertChip(), is("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk."));
    assertThat(selectBeverage(),
        is("Sie haben ein Getränk ausgewählt.Ihr Getränk wird vorbereitet.Gerät ist nun außer Betrieb."));
    assertThat(machine.getAmountOfBeverage(), is(0));

    assertThat(insertChip(), is("Gerät außer Betrieb."));
    assertThat(ecjectChip(), is("Gerät außer Betrieb."));
    assertThat(selectBeverage(), is("Gerät außer Betrieb."));
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
