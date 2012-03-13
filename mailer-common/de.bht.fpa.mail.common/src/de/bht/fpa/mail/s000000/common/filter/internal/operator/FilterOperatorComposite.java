package de.bht.fpa.mail.s000000.common.filter.internal.operator;

import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;

public abstract class FilterOperatorComposite<T> extends Composite {

  public FilterOperatorComposite(Composite parent, int style) {
    super(parent, style);
  }

  public abstract FilterOperator getFilterOperator();

  public abstract T getFilterValue();

}