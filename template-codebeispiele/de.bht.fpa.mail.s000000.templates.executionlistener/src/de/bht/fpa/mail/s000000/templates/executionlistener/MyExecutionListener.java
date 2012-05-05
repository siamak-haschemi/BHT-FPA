package de.bht.fpa.mail.s000000.templates.executionlistener;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;

public class MyExecutionListener implements IExecutionListener {

  @Override
  public void notHandled(String commandId, NotHandledException exception) {
    System.out.println("============ MyExecutionListener.notHandled ============");
    System.out.println("Command '" + commandId + "' not handeled: " + exception.getMessage());
    System.out.println("========================================================");
  }

  @Override
  public void postExecuteFailure(String commandId, ExecutionException exception) {
    System.out.println("======== MyExecutionListener.postExecuteFailure ========");
    System.out.println("Command '" + commandId + "' execution failed: " + exception.getMessage());
    System.out.println("========================================================");
  }

  @Override
  public void postExecuteSuccess(String commandId, Object returnValue) {
    System.out.println("======== MyExecutionListener.postExecuteSuccess ========");
    System.out.println("Command '" + commandId + "' execution succeeded. Return Value: " + returnValue);
    System.out.println("========================================================");
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    System.out.println("============ MyExecutionListener.preExecute ============");
    System.out.println("Command '" + commandId + "' pre-execution. Event:" + event);
    System.out.println("========================================================");
  }

}
