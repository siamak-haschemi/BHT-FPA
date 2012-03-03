package de.bht.fpa.modules.hello.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.bht.fpa.modules.hello.IHello;

public class Activator implements BundleActivator {
  @Override
  public void start(BundleContext bundleContext) throws Exception {
    IHello hello = new HelloImpl("Siamak");
    bundleContext.registerService(IHello.class, hello, null);
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
  }

}
