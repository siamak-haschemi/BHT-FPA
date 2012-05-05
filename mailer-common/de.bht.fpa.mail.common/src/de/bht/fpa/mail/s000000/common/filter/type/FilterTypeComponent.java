/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter.type;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.operator.BooleanFilterOperatorComposite;
import de.bht.fpa.mail.s000000.common.filter.operator.DefaultFilterOperatorComposite;
import de.bht.fpa.mail.s000000.common.filter.operator.FilterOperatorComposite;
import de.bht.fpa.mail.s000000.common.filter.operator.ImportanceFilterOperatorComposite;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

public final class FilterTypeComponent extends Composite {
  private final Composite filterPanel;
  private final StackLayout stackLayout;
  private ComboViewer filterTypeComboViewer;
  private FilterOperatorComposite<?> filterOperatorComposite;

  /**
   * Create the composite.
   * 
   * @param parent
   * @param style
   */
  public FilterTypeComponent(final Composite parent) {
    super(parent, SWT.NONE);
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    setLayout(layout);

    filterTypeComboViewer = new ComboViewer(this, SWT.READ_ONLY);
    final Combo combo = filterTypeComboViewer.getCombo();
    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));

    filterTypeComboViewer.setContentProvider(ArrayContentProvider.getInstance());
    filterTypeComboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        if (!(element instanceof FilterType)) {
          throw new IllegalArgumentException();
        }
        return ((FilterType) element).value();
      }
    });

    filterTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        if (stackLayout.topControl != null) {
          stackLayout.topControl.dispose();
        }
        FilterType selectedFilterType = getSelectedFilterType();
        switch (selectedFilterType) {
        case SENDER:
        case RECIPIENTS:
        case SUBJECT:
        case TEXT:
          filterOperatorComposite = new DefaultFilterOperatorComposite(filterPanel);
          break;
        case READ:
          filterOperatorComposite = new BooleanFilterOperatorComposite(filterPanel);
          break;
        case IMPORTANCE:
          filterOperatorComposite = new ImportanceFilterOperatorComposite(filterPanel);
          break;
        default:
          throw new IllegalArgumentException("Unknown filter type: " + selectedFilterType);
        }
        stackLayout.topControl = filterOperatorComposite;
        filterPanel.layout();
      }
    });

    filterTypeComboViewer.setInput(FilterType.values());

    filterPanel = new Composite(this, SWT.NONE);
    filterPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    stackLayout = new StackLayout();
    filterPanel.setLayout(stackLayout);

    filterTypeComboViewer.setSelection(new StructuredSelection(FilterType.SENDER));
  }

  @Override
  protected void checkSubclass() {
    // Disable the check that prevents subclassing of SWT components
  }

  public FilterType getFilterType() {
    return getSelectedFilterType();
  }

  public FilterOperator getFilterOperator() {
    return filterOperatorComposite.getFilterOperator();
  }

  public Object getFilterValue() {
    return filterOperatorComposite.getFilterValue();
  }

  private FilterType getSelectedFilterType() {
    return SelectionHelper.handleStructuredSelection(filterTypeComboViewer.getSelection(), FilterType.class);
  }
}
