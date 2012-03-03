package de.bht.fpa.modules.hello.stotter.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.bht.fpa.modules.hello.IHello;
import de.bht.fpa.modules.hello.stotter.HelloStotter;

public class Activator implements BundleActivator {

  @Override
  public void start(BundleContext bundleContext) throws Exception {
    IHello hello = new HelloStotter("Siamak");
    bundleContext.registerService(IHello.class, hello, null);
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
  }

}
