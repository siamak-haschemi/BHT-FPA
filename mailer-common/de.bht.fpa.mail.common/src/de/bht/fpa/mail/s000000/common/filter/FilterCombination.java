package de.bht.fpa.mail.s000000.common.filter;

/**
 * This class represents one filter entry of the {@link FilterDialog}.
 * 
 * @author siamakhaschemi
 */
public final class FilterCombination {
  private final FilterType filterType;
  private final FilterOperator filterOperator;
  private final Object value;

  public FilterCombination(FilterType filterType, FilterOperator filterOperator, Object value) {
    super();
    this.filterType = filterType;
    this.filterOperator = filterOperator;
    this.value = value;
  }

  public FilterType getFilterType() {
    return filterType;
  }

  public FilterOperator getFilterOperator() {
    return filterOperator;
  }

  public Object getFilterValue() {
    return value;
  }

}
