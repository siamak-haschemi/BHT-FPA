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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import de.bht.fpa.mail.s000000.common.internal.Activator;

/**
 * EditingSupport for JFace viewers that gets and sets the value using a nested
 * bean property string like "company.country.name".
 * 
 * @author Ralf Ebert <info@ralfebert.de>
 */
@SuppressWarnings("unchecked")
public class PropertyEditingSupport extends EditingSupport {

  /**
   * 
   */
  private static final long serialVersionUID = 5832990301061558748L;
  private final CellEditor cellEditor;
  private final IValue valueHandler;
  @SuppressWarnings("rawtypes")
  private final IValueFormatter valueFormatter;

  public PropertyEditingSupport(ColumnViewer viewer, String propertyName, CellEditor cellEditor) {
    this(viewer, new PropertyValue(propertyName), null, cellEditor);
  }

  @SuppressWarnings("rawtypes")
  public PropertyEditingSupport(ColumnViewer viewer, IValue valueHandler, IValueFormatter valueFormatter,
      CellEditor cellEditor) {
    super(viewer);
    this.valueHandler = valueHandler;
    this.valueFormatter = valueFormatter;
    this.cellEditor = cellEditor;
  }

  @Override
  protected CellEditor getCellEditor(Object element) {
    return cellEditor;
  }

  @Override
  protected boolean canEdit(Object element) {
    return true;
  }

  @Override
  protected Object getValue(Object element) {
    try {
      Object value = valueHandler.getValue(element);
      if (valueFormatter != null) {
        value = valueFormatter.format(value);
      }
      return value;
    } catch (Exception e) {
      // Exceptions are not re-thrown because EditingSupport is touchy in
      // that regard
      Activator.logException(e);
      return null;
    }
  }

  @Override
  protected void setValue(Object element, Object value) {
    try {
      Object parsedValue = value;
      if (valueFormatter != null) {
        parsedValue = valueFormatter.parse(value);
      }
      valueHandler.setValue(element, parsedValue);
      getViewer().refresh();
    } catch (Exception e) {
      // Exceptions are not re-thrown because EditingSupport is touchy in
      // that regard
      Activator.logException(e);
    }
  }

}
