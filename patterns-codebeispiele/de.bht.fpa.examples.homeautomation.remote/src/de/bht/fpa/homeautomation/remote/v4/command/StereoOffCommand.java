package de.bht.fpa.homeautomation.remote.v4.command;

import de.bht.fpa.homeautomation.vendors.Stereo;

public class StereoOffCommand extends AbstractCommand {
  private final Stereo stereo;

  public StereoOffCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  @Override
  public void execute() {
    stereo.off();
  }
}
