/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

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

  /**
   * Compare two strings using on of the {@link FilterOperator}s.
   * 
   * @param target
   *          the string against which the toMatch param is matched
   * @param toMatch
   *          the string which is matched against the target
   * @param filterOperator
   *          the {@link FilterOperator} which decides how the comparison is
   *          done
   * @return <code>true</code> if the strings match (based on the filterOperator
   *         param), or <code>false</code>.
   */
  public static boolean matches(String target, String toMatch, FilterOperator filterOperator) {
    if (target == null || toMatch == null) {
      return false;
    }
    switch (filterOperator) {
    case CONTAINS:
      return target.contains(toMatch);
    case CONTAINS_NOT:
      return !target.contains(toMatch);
    case ENDS_WITH:
      return target.endsWith(toMatch);
    case IS:
      return target.equals(toMatch);
    case STARTS_WITH:
      return target.startsWith(toMatch);
    default:
      throw new IllegalStateException("Unhandeled filter operator: " + filterOperator);
    }
  }
}
