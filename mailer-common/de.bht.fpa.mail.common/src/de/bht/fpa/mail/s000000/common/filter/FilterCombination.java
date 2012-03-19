/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

import java.io.Serializable;

/**
 * This class represents one filter entry of the {@link FilterDialog} consisting
 * of a {@link FilterType}, a {@link FilterOperator} and a filter value.
 * 
 * @author Siamak Haschemi
 */
public final class FilterCombination implements Serializable {
  private static final long serialVersionUID = -7278676570258198751L;
  private final FilterType filterType;
  private final FilterOperator filterOperator;
  private final Object value;

  public FilterCombination(FilterType filterType, FilterOperator filterOperator, Object value) {
    if (filterType == null) {
      throw new IllegalArgumentException("filterType is null");
    }
    if (filterOperator == null) {
      throw new IllegalArgumentException("filterOperator is null");
    }
    if (value == null) {
      throw new IllegalArgumentException("value is null");
    }

    this.filterType = filterType;
    this.filterOperator = filterOperator;
    this.value = value;
  }

  /**
   * Returns the {@link FilterType}.
   * 
   * @return the {@link FilterType} of the filter. It is guaranteed that the
   *         return value is not <code>null</code>.
   */
  public FilterType getFilterType() {
    return filterType;
  }

  /**
   * Returns the {@link FilterOperator}.
   * 
   * @return the {@link FilterOperator} of the filter. It is guaranteed that the
   *         return value is not <code>null</code>.
   */
  public FilterOperator getFilterOperator() {
    return filterOperator;
  }

  /**
   * Returns the filter value.
   * 
   * @return the value of the filter. It is guaranteed that the return value is
   *         not <code>null</code>.
   */
  public Object getFilterValue() {
    return value;
  }

}
