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
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

public final class ImportanceFilterOperatorComposite extends FilterOperatorComposite<Importance> {
  private final FilterOperator operator;
  private ComboViewer comboViewer;

  /**
   * @see de.bht.fpa.mail.s000000.common.filter.operator.FilterOperatorComposite#getFilterOperator()
   */
  @Override
  public FilterOperator getFilterOperator() {
    return operator;
  }

  @Override
  public Importance getFilterValue() {
    return SelectionHelper.handleStructuredSelection(comboViewer.getSelection(), Importance.class);
  }

  public ImportanceFilterOperatorComposite(Composite parent) {
    super(parent, SWT.NONE);
    setLayout(new GridLayout(2, false));

    Combo comboOperation = new Combo(this, SWT.READ_ONLY);
    comboOperation.setItems(new String[] { FilterOperator.IS.value() });
    comboOperation.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
    comboOperation.select(0);

    comboViewer = new ComboViewer(this, SWT.READ_ONLY);
    Combo comboValue = comboViewer.getCombo();
    comboValue.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
    comboViewer.setContentProvider(ArrayContentProvider.getInstance());
    comboViewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        return ((Importance) element).value();
      }
    });
    comboViewer.setInput(new Importance[] { Importance.HIGH, Importance.NORMAL, Importance.LOW });

    operator = FilterOperator.IS;
  }
}