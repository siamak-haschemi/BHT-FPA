package de.bht.fpa.homeautomation.remote.v4.command;

public interface ICommand {
  void execute();

  void undo();
}
