package de.bht.fpa.statepattern.v1;

public class CoffeeMachine {
  private static final int NO_CHIP = 0;
  private static final int CHIP_INSERTED = 1;
  private static final int BEVERAGE_SELECTED = 2;

  private int state = NO_CHIP;

  public CoffeeMachine() {
    System.out.println("\nWillkommen.");
  }

  public String insertChip() {
    StringBuilder sb = new StringBuilder();

    if (state == NO_CHIP) {
      sb.append("Sie haben einen Chip eingelegt. Wählen Sie ein Getränk.");
      state = CHIP_INSERTED;
    } else if (state == CHIP_INSERTED) {
      sb.append("Sie haben bereits einen Chip eingelegt.");
    } else if (state == BEVERAGE_SELECTED) {
      sb.append("Bitte warten Sie. Wir bereiten bereits Ihr Getränk zu.");
    }

    return sb.toString();
  }

  public String ecjectChip() {
    StringBuilder sb = new StringBuilder();

    if (state == NO_CHIP) {
      sb.append("Sie haben keinen Chip eingelegt den wir Ihnen auswerfen können.");
    } else if (state == CHIP_INSERTED) {
      sb.append("Ihr eingelegter Chip wird ausgeworfen.");
      state = NO_CHIP;
    } else if (state == BEVERAGE_SELECTED) {
      sb.append("Ihr Chip kann nicht ausgeworfen werden, da Sie bereits ein Getränk gewählt haben.");
    }

    return sb.toString();
  }

  public String selectBeverage() {
    StringBuilder sb = new StringBuilder();

    if (state == NO_CHIP) {
      sb.append("Sie haben ein Getränk ausgewählt, aber kein Chip eingeworfen.");
    } else if (state == CHIP_INSERTED) {
      sb.append("Sie haben ein Getränk ausgewählt.");
      state = BEVERAGE_SELECTED;
      sb.append(dispenseBeverage());
    } else if (state == BEVERAGE_SELECTED) {
      sb.append("Sie haben bereits ein Getränk gewählt.");
    }

    return sb.toString();
  }

  private String dispenseBeverage() {
    StringBuilder sb = new StringBuilder();

    if (state == NO_CHIP) {
      sb.append("Sie müssen zuerst einen Chip einlegen.");
    } else if (state == CHIP_INSERTED) {
      sb.append("Sie haben ein Chip eingelegt, aber noch kein ein Getränk ausgewählt.");
    } else if (state == BEVERAGE_SELECTED) {
      sb.append("Ihr Getränk wird vorbereitet.");
      state = NO_CHIP;
    }

    return sb.toString();
  }
  // ...
}
