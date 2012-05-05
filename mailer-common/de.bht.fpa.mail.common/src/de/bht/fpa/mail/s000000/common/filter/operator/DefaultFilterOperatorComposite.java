/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter.operator;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

public final class DefaultFilterOperatorComposite extends FilterOperatorComposite<String> {
  private FilterOperator operator;
  private Text text;

  /**
   * @see de.bht.fpa.mail.s000000.common.filter.operator.FilterOperatorComposite#getFilterOperator()
   */
  @Override
  public FilterOperator getFilterOperator() {
    return operator;
  }

  @Override
  public String getFilterValue() {
    return text.getText();
  }

  public DefaultFilterOperatorComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new GridLayout(2, false));

    ComboViewer comboViewer = new ComboViewer(this, SWT.READ_ONLY);
    Combo combo = comboViewer.getCombo();
    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    comboViewer.setContentProvider(ArrayContentProvider.getInstance());
    comboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        if (!(element instanceof FilterOperator)) {
          throw new IllegalArgumentException();
        }
        return ((FilterOperator) element).value();
      }
    });
    comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        operator = SelectionHelper.handleStructuredSelectionEvent(event, FilterOperator.class);
      }
    });
    comboViewer.setInput(FilterOperator.values());

    // default
    comboViewer.setSelection(new StructuredSelection(FilterOperator.CONTAINS));

    text = new Text(this, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
  }
}