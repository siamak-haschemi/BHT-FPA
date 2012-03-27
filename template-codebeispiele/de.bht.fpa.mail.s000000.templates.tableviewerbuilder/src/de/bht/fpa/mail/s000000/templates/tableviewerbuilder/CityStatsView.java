package de.bht.fpa.mail.s000000.templates.tableviewerbuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.ICellFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class CityStatsView extends ViewPart {

  private static final double AREA_KM2_MAX = 800d;
  private static final double AREA_KM2_MIN = 100d;
  private static final int PUPULATION_MAX = 10000000;
  private static final int PUPULATION_MIN = 10000;
  private static final int NR_OF_TEST_CITIES = 50;
  private static final int BEIGHBOUR_CITY_PERCENT_WITH = 100;
  private static final int FOUNDING_DATE_PERCENT_WITH = 100;
  private static final int CITY_COLUMN_PERCENT_WIDTH = 60;
  private static final int BIG_CITY_MIN = 5000000;
  private TableViewer tableViewer;

  @Override
  public void createPartControl(Composite parent) {

    TableViewerBuilder t = new TableViewerBuilder(parent);

    ColumnBuilder city = t.createColumn("City");
    city.bindToProperty("name");
    city.setPercentWidth(CITY_COLUMN_PERCENT_WIDTH);
    city.useAsDefaultSortColumn();
    city.makeEditable();
    city.build();

    ColumnBuilder population = t.createColumn("Population");
    population.bindToProperty("stats.population");
    population.format(Formatter.forInt(new DecimalFormat("#,##0")));
    population.format(new ICellFormatter() {

      @Override
      public void formatCell(ViewerCell cell, Object value) {
        int population = (Integer) value;
        int color = SWT.COLOR_BLACK;
        if (population > BIG_CITY_MIN) {
          color = SWT.COLOR_RED;
        }
        cell.setForeground(cell.getControl().getDisplay().getSystemColor(color));
      }
    });
    population.alignRight();
    population.makeEditable(Formatter.forInt());
    population.build();

    ColumnBuilder area = t.createColumn("Area");
    area.bindToProperty("stats.areaKm2");
    area.alignRight();
    area.format(Formatter.forDouble(new DecimalFormat("0.00 km²")));
    area.makeEditable(Formatter.forDouble(new DecimalFormat("0.00")));
    area.build();

    ColumnBuilder density = t.createColumn("People/km²");
    density.bindToValue(new BaseValue<City>() {
      @Override
      public Object get(City city) {
        return city.getStats().getPopulation() / city.getStats().getAreaKm2();
      }
    });
    density.format(Formatter.forDouble(new DecimalFormat("0")));
    density.alignRight();
    density.build();

    ColumnBuilder foundingDate = t.createColumn("Founding date");
    foundingDate.bindToProperty("foundingDate");
    StringValueFormatter dateFormat = Formatter.forDate(SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM));
    foundingDate.format(dateFormat);
    foundingDate.alignCenter();
    foundingDate.setPixelWidth(FOUNDING_DATE_PERCENT_WITH);
    foundingDate.makeEditable(dateFormat);
    foundingDate.build();

    ColumnBuilder neighborCity = t.createColumn("Neighbor city");
    neighborCity.bindToProperty("neighborCity");
    neighborCity.setPixelWidth(BEIGHBOUR_CITY_PERCENT_WITH);
    ComboBoxViewerCellEditor cityComboEditor = new ComboBoxViewerCellEditor(t.getTable(), SWT.READ_ONLY);
    cityComboEditor.setContentProvider(ArrayContentProvider.getInstance());
    cityComboEditor.setLabelProvider(new LabelProvider());
    cityComboEditor.setInput(RandomData.CITIES);
    neighborCity.makeEditable(cityComboEditor);
    neighborCity.build();

    t.setInput(createSomeData());
    tableViewer = t.getTableViewer();

  }

  private List<City> createSomeData() {
    List<City> data = new ArrayList<City>();
    RandomData randomData = new RandomData();
    for (int i = 0; i < NR_OF_TEST_CITIES; i++) {
      int population = randomData.someNumber(PUPULATION_MIN, PUPULATION_MAX);
      double areaKm2 = randomData.someNumber(AREA_KM2_MIN, AREA_KM2_MAX);
      CityStats stats = new CityStats(population, areaKm2);

      data.add(new City(randomData.someCity(), new Date(), stats, randomData.someCity()));
      randomData.newData();
    }
    return data;
  }

  @Override
  public void setFocus() {
    tableViewer.getTable().setFocus();
  }

}
