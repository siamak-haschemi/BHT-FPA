/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the filter operation.
 * 
 * @author Siamak Haschemi
 * 
 */
public enum FilterOperator {
  CONTAINS("contains"), CONTAINS_NOT("contains not"), STARTS_WITH("starts with"), ENDS_WITH("ends with"), IS("is");

  private String value;

  private FilterOperator(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
