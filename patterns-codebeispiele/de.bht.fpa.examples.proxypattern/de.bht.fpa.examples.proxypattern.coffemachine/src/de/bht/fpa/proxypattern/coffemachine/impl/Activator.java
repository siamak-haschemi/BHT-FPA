package de.bht.fpa.proxypattern.coffemachine.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;

public class Activator implements BundleActivator {

  private ServiceRegistration<ICoffeMachine> serviceRegistration;

  @Override
  public void start(BundleContext bundleContext) throws Exception {
    serviceRegistration = bundleContext.registerService(ICoffeMachine.class, new CoffeeMachine("INI-Raum", 30), null);
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
  }

}
