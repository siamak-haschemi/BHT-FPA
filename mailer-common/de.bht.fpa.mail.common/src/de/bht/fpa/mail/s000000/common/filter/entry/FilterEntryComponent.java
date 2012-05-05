/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter.entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.type.FilterTypeComponent;

public final class FilterEntryComponent extends Composite {
  private static final int NR_OF_COLUMNS = 3;
  private IFilterEntryChangedListener filterChangedListener = IFilterEntryChangedListener.NULL;
  private final FilterTypeComponent filterTypeComponent;
  private final Button remBtn;

  /**
   * Create the composite.
   * 
   * @param parent
   * @param style
   */
  public FilterEntryComponent(final Composite parent) {
    super(parent, SWT.NONE);
    GridLayout layout = new GridLayout(NR_OF_COLUMNS, false);
    layout.marginHeight = 0;
    setLayout(layout);

    filterTypeComponent = new FilterTypeComponent(this);
    filterTypeComponent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    remBtn = new Button(this, SWT.NONE);
    remBtn.setText("-");
    remBtn.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        filterChangedListener.onRemoveFilter(FilterEntryComponent.this);
      }
    });

    Button addBtn = new Button(this, SWT.NONE);
    addBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    addBtn.setText("+");
    addBtn.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        filterChangedListener.onAddFilter(FilterEntryComponent.this);
      }
    });
  }

  public void setFilterChangedListener(IFilterEntryChangedListener filterChangedListener) {
    this.filterChangedListener = filterChangedListener;
  }

  public void setRemoveButtonEnabled(boolean removeButtonEnabled) {
    remBtn.setEnabled(removeButtonEnabled);
  }

  @Override
  protected void checkSubclass() {
    // Disable the check that prevents subclassing of SWT components
  }

  public FilterCombination getFilterCombination() {
    return new FilterCombination(filterTypeComponent.getFilterType(), filterTypeComponent.getFilterOperator(),
        filterTypeComponent.getFilterValue());
  }
}
