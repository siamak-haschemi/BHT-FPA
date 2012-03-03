package de.bht.fpa.modules.hello.main;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.bht.fpa.modules.hello.IHello;

public class Activator implements BundleActivator {

  @Override
  public void start(BundleContext bundleContext) throws Exception {
    Collection<ServiceReference<IHello>> serviceReferences = bundleContext.getServiceReferences(IHello.class, null);

    for (ServiceReference<IHello> serviceReference : serviceReferences) {
      IHello hello = bundleContext.getService(serviceReference);
      System.out.println(hello.sayHello());
    }
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
  }
}
