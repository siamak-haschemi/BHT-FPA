/*******************************************************************************
 * Copyright (c) 2008 Ralf Ebert
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ralf Ebert - initial API and implementation
 *******************************************************************************/
package de.ralfebert.rcputils.properties;

import de.bht.fpa.mail.s000000.common.internal.Activator;

/**
 * PropertyValueFormatter describes a conversion from a object to some property
 * of the object.
 * 
 * @author Ralf Ebert <info@ralfebert.de>
 */
public class PropertyValueFormatter implements IValueFormatter<Object, Object> {

  private final IValue valueHandler;

  public PropertyValueFormatter(String propertyName) {
    this.valueHandler = new PropertyValue(propertyName);
  }

  @Override
  public Object format(Object obj) {
    try {
      return valueHandler.getValue(obj);
    } catch (Exception e) {
      Activator.logException(e);
      return null;
    }
  }

  @Override
  public Object parse(Object obj) {
    throw new UnsupportedOperationException();
  }
}
