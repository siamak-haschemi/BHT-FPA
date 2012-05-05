package de.bht.fpa.mail.s000000.templates.filterdialog;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;

public final class Main {
  private Main() {

  }

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);

    FilterDialog filterDialog = new FilterDialog(shell);
    filterDialog.open();

    List<FilterCombination> filterCombinations = filterDialog.getFilterCombinations();

    FilterGroupType filterGroupType = filterDialog.getFilterGroupType();
    System.out.println("Grouping: " + filterGroupType);
    for (FilterCombination filterCombination : filterCombinations) {
      FilterType filterType = filterCombination.getFilterType();
      FilterOperator filterOperator = filterCombination.getFilterOperator();
      Object filterValue = filterCombination.getFilterValue();
      System.out.println("Filter: type:" + filterType + " operator:" + filterOperator + " value:" + filterValue);
    }
    display.dispose();
  }
}
