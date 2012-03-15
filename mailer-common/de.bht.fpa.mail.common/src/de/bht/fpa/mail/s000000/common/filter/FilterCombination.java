package de.bht.fpa.mail.s000000.common.filter;

import java.io.Serializable;

/**
 * This class represents one filter entry of the {@link FilterDialog} consisting
 * of a {@link FilterType}, a {@link FilterOperator} and a filter value.
 * 
 * @author siamakhaschemi
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
   * @return the {@link FilterType} of the filter. It is guaranteed that the
   *         return value is not <code>null</code>.
   */
  public FilterType getFilterType() {
    return filterType;
  }

  /**
   * @return the {@link FilterOperator} of the filter. It is guaranteed that the
   *         return value is not <code>null</code>.
   */
  public FilterOperator getFilterOperator() {
    return filterOperator;
  }

  /**
   * @return the value of the filter. It is guaranteed that the return value is
   *         not <code>null</code>.
   */
  public Object getFilterValue() {
    return value;
  }

}
