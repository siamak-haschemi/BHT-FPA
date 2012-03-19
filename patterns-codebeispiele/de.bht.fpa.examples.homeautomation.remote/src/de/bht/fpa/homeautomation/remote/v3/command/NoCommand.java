package de.bht.fpa.homeautomation.remote.v3.command;

import de.bht.fpa.homeautomation.remote.v3.command.NoCommand;
import de.bht.fpa.homeautomation.remote.v3.command.ICommand;

/**
 * This class uses the Null Object Pattern. It implements the {@link ICommand},
 * but does not implement the {@link ICommand#execute()} and the
 * {@link ICommand#undo()}.
 * 
 * @author Siamak Haschemi
 */
public class NoCommand implements ICommand {

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
  }

  @Override
  public void undo() {
    System.out.println(this.getClass().getSimpleName() + ".undo()");
  }

}
