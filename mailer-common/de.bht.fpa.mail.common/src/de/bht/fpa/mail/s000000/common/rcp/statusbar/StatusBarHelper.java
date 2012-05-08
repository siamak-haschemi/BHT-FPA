/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.rcp.statusbar;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
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
  private static class NullStatusLineManager extends StatusLineManager {
    @Override
    public void setMessage(String message) {
      System.out.println("StatusLine found. Message was: " + message);
    };

    @Override
    public void setMessage(org.eclipse.swt.graphics.Image image, String message) {
      System.out.println("StatusLine found. Message was: " + message);
    };

    @Override
    public void setErrorMessage(String message) {
      System.err.println("StatusLine found. Message was: " + message);
    };

    @Override
    public void setErrorMessage(org.eclipse.swt.graphics.Image image, String message) {
      System.err.println("StatusLine found. Message was: " + message);
    };
  }

  private static IStatusLineManager manager = null;

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
  public static synchronized IStatusLineManager getStatusLineManager() {
    if (manager != null) {
      return manager;
    }

    manager = new NullStatusLineManager();

    IWorkbench wb = PlatformUI.getWorkbench();
    if (wb == null) {
      return manager;
    }

    IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
    if (win == null) {
      return manager;
    }

    IWorkbenchPage page = win.getActivePage();
    if (page == null) {
      return manager;
    }

    IWorkbenchPart part = page.getActivePart();
    if (part == null) {
      return manager;
    }

    IWorkbenchPartSite site = part.getSite();
    if (site == null || !(site instanceof IViewSite)) {
      return manager;
    }
    IViewSite vSite = (IViewSite) site;

    IActionBars actionBars = vSite.getActionBars();
    if (actionBars == null) {
      return manager;
    }

    manager = actionBars.getStatusLineManager();
    return manager;
  }

  public static void setMessage(String message) {
    getStatusLineManager().setMessage(message);
  }
}
