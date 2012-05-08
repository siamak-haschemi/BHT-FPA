package de.bht.fpa.mail.s000000.templates.executionlistener;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

public class Startup implements IStartup {

  @Override
  public void earlyStartup() {
    final IWorkbench workbench = PlatformUI.getWorkbench();

    ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
    commandService.addExecutionListener(new MyExecutionListener());
  }
}
