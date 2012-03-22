/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.rcp.selection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * This class helps with handling selections on Eclipse Views.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class SelectionHelper {

  /**
   * Utility classes do not need to be instantiated.
   */
  private SelectionHelper() {
  }

  /**
   * This method can be used to unwrap the selected object, which is wrapped by
   * the {@link ISelection} interface. It assumes the {@link ISelection} is
   * actually a {@link IStructuredSelection} and wraps only one element (single
   * selection). The returns value may be <code>null</code>.
   * 
   * @param selection
   *          the {@link ISelection} to unwrap
   * @param clazz
   *          the type of the object which is wrapped
   * @return the unwrapped object, or <code>null</code> if :
   *         <ul>
   *         <li>the original {@link ISelection} is <code>null</code></li>
   *         <li>the searched type is <code>null</code></li>
   *         <li>the searched type and the actual type of the wrapped object do
   *         not match</li>
   *         </ul>
   */
  @SuppressWarnings("unchecked")
  public static <T> T handleStructuredSelection(ISelection selection, Class<T> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("clazz should not be null");
    }
    if (selection == null || !(selection instanceof IStructuredSelection)) {
      return null;
    }
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    Object firstElement = structuredSelection.getFirstElement();

    if (firstElement == null) {
      return null;
    }

    if (!clazz.isAssignableFrom(firstElement.getClass())) {
      return null;
    }
    return (T) firstElement;
  }

  /**
   * This method can be used to unwrap the selected object, which is wrapped by
   * the {@link ISelection} of a {@link SelectionChangedEvent}. The return value
   * may be <code>null</code>.
   * 
   * @param event
   *          the {@link SelectionChangedEvent} giving access to the
   *          {@link ISelection} to unwrap
   * @param clazz
   *          the type of the object which is wrapped
   * @return the unwrapped object, or <code>null</code> if :
   *         <ul>
   *         <li>the original {@link ISelection} is <code>null</code></li>
   *         <li>the searched type is <code>null</code></li>
   *         <li>the searched type and the actual type of the wrapped object do
   *         not match</li>
   *         </ul>
   */
  public static <T> T handleStructuredSelectionEvent(SelectionChangedEvent event, Class<T> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("clazz should not be null");
    }
    if (event == null) {
      return null;
    }
    return handleStructuredSelection(event.getSelection(), clazz);
  }
}
