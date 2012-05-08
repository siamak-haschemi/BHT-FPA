/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter.operator;

import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;

public abstract class FilterOperatorComposite<T> extends Composite {
  public FilterOperatorComposite(Composite parent, int style) {
    super(parent, style);
  }

  public abstract FilterOperator getFilterOperator();

  public abstract T getFilterValue();

}