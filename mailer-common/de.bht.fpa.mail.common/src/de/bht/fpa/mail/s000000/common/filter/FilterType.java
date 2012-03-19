/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

/**
 * Enumeration for the fields of the email to filter.
 * 
 * @author Siamak Haschemi
 */
public enum FilterType {
  SENDER("Sender"), RECIPIENTS("Recipients"), SUBJECT("Subject"), TEXT("Contents of EMail"), READ("Read"), IMPORTANCE(
      "Importance");

  private String value;

  private FilterType(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}
