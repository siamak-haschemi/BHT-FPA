package de.bht.fpa.homeautomation.remote.v3.command;

public interface ICommand {
  void execute();

  void undo();
}
