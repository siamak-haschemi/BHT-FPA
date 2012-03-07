package de.bht.fpa.mail.s000000.common;

/*******************************************************************************
 * Copyright (c) 2009 Jan Petranek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class NativeDialogFactory is a configurable Dialog factory.
 * 
 * By default, it handles user dialogs with native dialogs (SWT). When the state
 * is set to TESTING, it displays stand-in dialogs. Those stand-ins may be way
 * simpler than the native widgets, but they are accessible by SWTBot.
 * 
 * @author Jan Petranek
 */
public class NativeDialogFactory {

  public NativeDialogFactory() {
  }

  private static final class DummyInputValidator implements IInputValidator {
    @Override
    public String isValid(String newText) {
      return null;
    }
  }

  /**
   * The Enumeration DialogState, used to indicate the mode of operation.
   */
  public enum OperationMode {

    /** The DEFAULT state, for normal operation. */
    DEFAULT,
    /**
     * The TESTING state, used to indicate SWTBot-Testing needs stand-in
     * dialogs.
     */
    TESTING
  };

  /** The mode of operation. */
  private static OperationMode mode = OperationMode.DEFAULT;

  /**
   * Sets the operation mode.
   * 
   * @param state
   *          the desired operation mode
   */
  public static void setMode(OperationMode pMode) {
    mode = pMode;
  }

  /**
   * Gets the operation mode.
   * 
   * @return the current operation mode
   */
  public static OperationMode getMode() {
    return mode;
  }

  public String directorySelectionDialog(Shell shell, String message, String text, String filterPath) {
    switch (getMode()) {
    case DEFAULT:
      DirectoryDialog dialog = new DirectoryDialog(shell);
      dialog.setMessage(text);
      dialog.setText(text);
      return dialog.open();

    case TESTING:
      InputDialog testDirectoryDialog = new InputDialog(shell, text, text, "", new DummyInputValidator());
      testDirectoryDialog.open();
      return testDirectoryDialog.getValue();

    default:
      throw new RuntimeException("unknown state " + getMode());
    }
  }
}
