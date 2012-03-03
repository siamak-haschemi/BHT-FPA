package de.bht.fpa.homeautomation.remote.v4.command;

import de.bht.fpa.homeautomation.vendors.Stereo;

public class StereoWithDvdOnCommand extends AbstractCommand {
  private final Stereo stereo;

  public StereoWithDvdOnCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  @Override
  public void execute() {
    stereo.on();
    stereo.setDvd();
    stereo.setVolume(11);
  }
}
