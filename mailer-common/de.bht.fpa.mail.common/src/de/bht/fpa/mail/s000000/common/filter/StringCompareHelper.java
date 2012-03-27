package de.bht.fpa.mail.s000000.common.filter;

/**
 * This class helps with comparing strings. It uses the enumeration
 * {@link FilterOperator} for choosing the comparison operation.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class StringCompareHelper {
  private StringCompareHelper() {

  }

  public static boolean matches(String a, String b, FilterOperator filterOperator) {
    if (a == null || b == null) {
      return false;
    }
    switch (filterOperator) {
    case CONTAINS:
      return a.contains(b);
    case CONTAINS_NOT:
      return !a.contains(b);
    case ENDS_WITH:
      return a.endsWith(b);
    case IS:
      return a.equals(b);
    case STARTS_WITH:
      return a.startsWith(b);
    default:
      throw new IllegalStateException("Unhandeled filter operator: " + filterOperator);
    }
  }
}
