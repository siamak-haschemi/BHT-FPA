package de.bht.fpa.mail.s000000.common.swt;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class DirectoryDialog {
  public static final String SYSTEM_PROPERTY_KEY = DirectoryDialog.class.getName();
  public static final String SYSTEM_PROPERTY_VALUE = "test";

  private final Shell parent;
  private String text = null;
  private String message = null;
  private String filterPath = null;

  public DirectoryDialog(Shell parent) {
    this.parent = parent;
  }

  public String open() {
    String prop = System.getProperty(SYSTEM_PROPERTY_KEY);

    if (prop == null || !prop.equalsIgnoreCase(SYSTEM_PROPERTY_VALUE)) {
      return openOriginalDirectoryDialog();
    }
    return openInputDialog();
  }

  private String openInputDialog() {
    InputDialog inputDialog = new InputDialog(parent, text, message, "", null);
    if (inputDialog.open() != Window.OK) {
      return null;
    }
    return inputDialog.getValue();
  }

  private String openOriginalDirectoryDialog() {
    org.eclipse.swt.widgets.DirectoryDialog directoryDialog = new org.eclipse.swt.widgets.DirectoryDialog(parent);
    if (message != null) {
      directoryDialog.setMessage(message);
    }

    if (text != null) {
      directoryDialog.setText(text);
    }

    if (filterPath != null) {
      directoryDialog.setFilterPath(filterPath);
    }
    return directoryDialog.open();
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setFilterPath(String filterPath) {
    this.filterPath = filterPath;
  }
}
