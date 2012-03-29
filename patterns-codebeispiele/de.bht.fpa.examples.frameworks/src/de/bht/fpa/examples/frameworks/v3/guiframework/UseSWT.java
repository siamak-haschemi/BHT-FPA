package de.bht.fpa.examples.frameworks.v3.guiframework;

import org.apache.commons.validator.routines.EmailValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.bht.fpa.examples.frameworks.Person;

public final class UseSWT {
  private static Text txtName;
  private static StyledText txtEmail;
  private static Button btnSubmit;

  private UseSWT() {

  }

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setSize(300, 150);
    shell.setLayout(new GridLayout(2, false));

    Label lblName = new Label(shell, SWT.NONE);
    lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblName.setText("What is your Name?");

    txtName = new Text(shell, SWT.BORDER);
    txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    Label lblEmail = new Label(shell, SWT.NONE);
    lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblEmail.setText("What is your E-Mail?");

    txtEmail = new StyledText(shell, SWT.BORDER);
    txtEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    txtEmail.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        if (emailIsValid(txtEmail.getText())) {
          txtEmail.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
          btnSubmit.setEnabled(true);
        } else {
          txtEmail.setForeground(display.getSystemColor(SWT.COLOR_RED));
          btnSubmit.setEnabled(false);
        }
      }
    });

    btnSubmit = new Button(shell, SWT.NONE);
    btnSubmit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
    btnSubmit.setText("Submit");

    btnSubmit.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Person person = new Person(txtName.getText(), txtEmail.getText());
        System.out.println(person);
      }
    });

    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  private static boolean emailIsValid(String email) {
    // use the apache commons validator library
    return EmailValidator.getInstance().isValid(email);
  }
}
