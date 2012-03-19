/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the filter grouping type.
 * 
 * @author Siamak Haschemi
 */
public enum FilterGroupType {
  UNION("one"), INTERSECTION("all");

  private String value;

  private FilterGroupType(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
