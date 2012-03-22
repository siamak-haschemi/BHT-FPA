/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.rcp.statusbar;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * This class helps with getting an {@link IStatusLineManager} instance.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class StatusBarHelper {

  /**
   * Utility classes do not need to be instantiated.
   */
  private StatusBarHelper() {

  }

  /**
   * This method returns the {@link IStatusLineManager}. It may return
   * <code>null</code>.
   * 
   * @return {@link IStatusLineManager} or <code>null</code>
   */
  public static IStatusLineManager getStatusLineManager() {
    IWorkbench wb = PlatformUI.getWorkbench();
    if (wb == null) {
      return null;
    }

    IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
    if (win == null) {
      return null;
    }

    IWorkbenchPage page = win.getActivePage();
    if (page == null) {
      return null;
    }

    IWorkbenchPart part = page.getActivePart();
    if (part == null) {
      return null;
    }

    IWorkbenchPartSite site = part.getSite();
    if (site == null || !(site instanceof IViewSite)) {
      return null;
    }
    IViewSite vSite = (IViewSite) site;

    IActionBars actionBars = vSite.getActionBars();
    if (actionBars == null) {
      return null;
    }

    return actionBars.getStatusLineManager();
  }
}
