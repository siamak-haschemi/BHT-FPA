package de.bht.fpa.proxypattern.monitor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;

public class Activator implements BundleActivator {
  @Override
  public void start(BundleContext context) throws Exception {
    ServiceReference<ICoffeMachine> serviceReference = context.getServiceReference(ICoffeMachine.class);
    if (serviceReference == null) {
      return;
    }

    ICoffeMachine coffeMachine = context.getService(serviceReference);
    if (coffeMachine == null) {
      return;
    }

    coffeMachine.insertChip();

    System.out.println("Coffe Machine: " + coffeMachine.getLocation());
    System.out.println("Current Capacity: " + coffeMachine.getCapacity());
    System.out.println("Current State: " + coffeMachine.getState());
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    System.out.println("Monitor stopped!");
  }
}
