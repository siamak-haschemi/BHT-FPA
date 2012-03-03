package de.bht.fpa.homeautomation.remote.v4.command;

public class MacroCommand extends AbstractCommand {
  private final ICommand[] commands;

  public MacroCommand(ICommand... commands) {
    this.commands = commands;
  }

  @Override
  public void execute() {
    System.out.println(this.getClass().getSimpleName() + ".execute()");
    for (ICommand command : commands) {
      command.execute();
    }
  }
}
