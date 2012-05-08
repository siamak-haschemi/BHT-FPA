/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogLabelKeys;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.bht.fpa.mail.s000000.common.filter.entry.FilterEntryComponent;
import de.bht.fpa.mail.s000000.common.filter.entry.IFilterEntryChangedListener;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

/**
 * This {@link Dialog} allows to create a filter combination. The user can
 * choose the grouping (union, intersection) and add an unlimited amount of
 * child filters. <br/>
 * A typical usage of this class is
 * 
 * <pre>
 * ...
 * Shell shell = ...
 * FilterDialog filterDialog = new FilterDialog(shell);
 * filterDialog.open(); // opens modal window
 * 
 * FilterGroupType filterGroupType = filterDialog.getFilterGroupType(); // the grouping type, union or intersection
 * List<FilterCombination> filterCombinations = filterDialog.getFilterCombinations(); // the list of child filters
 * for (FilterCombination filterCombination : filterCombinations) {
 *    FilterType filterType = filterCombination.getFilterType(); // sender, receiver, subject, ...
 *    FilterOperator filterOperator = filterCombination.getFilterOperator(); // contains, contains not, is, ...
 *    Object filterValue = filterCombination.getFilterValue();
 *  }
 * ...
 * </pre>
 * 
 * @author Siamak Haschemi
 * 
 */
public final class FilterDialog extends Dialog {
  private static final String DIALOG_TITLE = "Filter Configuration";
  private static final int HEIGHT = 300;
  private static final int WIDTH = 600;
  private static final int NR_OF_COLUMNS = 3;

  private List<FilterCombination> filterCombinations;
  private FilterGroupType filterGroupType;

  private final List<FilterEntryComponent> filterEntryComponents = new LinkedList<FilterEntryComponent>();
  private final IFilterEntryChangedListener filterEntryChangedListener = new IFilterEntryChangedListener() {
    @Override
    public void onAddFilter(FilterEntryComponent filterEntryComponent) {
      int indexOf = filterEntryComponents.indexOf(filterEntryComponent);
      addNewFilterEntryAtIndex(indexOf);
      container.layout();
    }

    @Override
    public void onRemoveFilter(FilterEntryComponent filterEntryComponent) {
      removeFilterEntry(filterEntryComponent);
      container.layout();
    }
  };
  private Group filterEntriesGroup;
  private Composite container;
  private ComboViewer groupFilterComboViewer;

  /**
   * Create the dialog.
   * 
   * @param parentShell
   */
  public FilterDialog(Shell parentShell) {
    super(parentShell);
    setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE);
  }

  /**
   * Create contents of the dialog.
   * 
   * @param parent
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    container = (Composite) super.createDialogArea(parent);
    container.setLayout(new GridLayout(1, false));
    getShell().setText(DIALOG_TITLE);

    addUnionIntersection();
    addFilterEntryGroup();
    addNewFilterEntryAtIndex(0);
    return container;
  }

  private void addFilterEntryGroup() {

    filterEntriesGroup = new Group(container, SWT.NONE);
    GridLayout layout = new GridLayout(1, false);
    layout.marginHeight = 0;
    filterEntriesGroup.setLayout(layout);
    filterEntriesGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
  }

  private void addUnionIntersection() {
    Composite unionIntersectionComponent = new Composite(container, SWT.NONE);
    unionIntersectionComponent.setLayout(new GridLayout(NR_OF_COLUMNS, false));

    Label lblBeiErfllen = new Label(unionIntersectionComponent, SWT.NONE);
    lblBeiErfllen.setText("If");

    groupFilterComboViewer = new ComboViewer(unionIntersectionComponent, SWT.READ_ONLY);
    groupFilterComboViewer.setContentProvider(ArrayContentProvider.getInstance());
    groupFilterComboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        return ((FilterGroupType) element).value();
      }
    });
    groupFilterComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        filterGroupType = SelectionHelper.handleStructuredSelectionEvent(event, FilterGroupType.class);
      }
    });
    groupFilterComboViewer.setInput(FilterGroupType.values());
    groupFilterComboViewer.setSelection(new StructuredSelection(FilterGroupType.UNION));

    Label lblDerFolgendenBedingungen = new Label(unionIntersectionComponent, SWT.NONE);
    lblDerFolgendenBedingungen.setText("of the following conditions are met:");
  }

  private void addNewFilterEntryAtIndex(int index) {
    FilterEntryComponent filterEntryComponent = new FilterEntryComponent(filterEntriesGroup);
    filterEntryComponent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    filterEntryComponent.setFilterChangedListener(filterEntryChangedListener);
    filterEntryComponents.add(index, filterEntryComponent);
    updateRemoveButtonEnabled();
  }

  private void removeFilterEntry(FilterEntryComponent filterEntryComponent) {
    filterEntryComponents.remove(filterEntryComponent);
    filterEntryComponent.dispose();
    updateRemoveButtonEnabled();
  }

  private void updateRemoveButtonEnabled() {
    if (filterEntryComponents.size() == 1) {
      filterEntryComponents.get(0).setRemoveButtonEnabled(false);
    } else {
      for (FilterEntryComponent filterEntryComponent : filterEntryComponents) {
        filterEntryComponent.setRemoveButtonEnabled(true);
      }
    }
  }

  /**
   * Create contents of the button bar.
   * 
   * @param parent
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    String ok = JFaceResources.getString(IDialogLabelKeys.OK_LABEL_KEY);
    String cancel = JFaceResources.getString(IDialogLabelKeys.CANCEL_LABEL_KEY);

    createButton(parent, IDialogConstants.OK_ID, ok, true);
    createButton(parent, IDialogConstants.CANCEL_ID, cancel, false);
  }

  /**
   * Return the initial size of the dialog.
   */
  @Override
  protected Point getInitialSize() {
    return new Point(WIDTH, HEIGHT);
  }

  @Override
  protected void okPressed() {
    filterCombinations = new LinkedList<FilterCombination>();
    for (FilterEntryComponent filterEntryComponent : filterEntryComponents) {
      filterCombinations.add(filterEntryComponent.getFilterCombination());
    }
    super.okPressed();
  }

  @Override
  protected void cancelPressed() {
    filterEntriesGroup = null;
    filterCombinations = null;
    super.cancelPressed();
  }

  /**
   * Returns the result of this filter dialog, which is a list of filters
   * created by the user, or <code>null</code> if the ser canceled the dialog (
   * see {@link FilterDialog#cancelPressed()}).
   * 
   * @return list of {@link FilterCombination}s or <code>null</code> if the user
   *         canceled the dialog.
   */
  public List<FilterCombination> getFilterCombinations() {
    return filterCombinations;
  }

  /**
   * Returns the grouping type of the filter, or <code>null</code> if the user
   * canceled the dialog ( see {@link FilterDialog#cancelPressed()}).
   * 
   * @return
   */
  public FilterGroupType getFilterGroupType() {
    return filterGroupType;
  }
}
