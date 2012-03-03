package de.bht.fpa.proxypattern.coffemachine.proxy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;

public class Activator implements BundleActivator {
  private ServiceTracker<ICoffeMachine, ServiceReference<ICoffeMachine>> serviceTracker = null;
  private ServiceRegistration<ICoffeMachine> coffeMachineProxyRegistration;
  private CoffeMachineProxy coffeMachineProxy;

  @Override
  public void start(final BundleContext bundleContext) {
    try {
      coffeMachineProxy = new CoffeMachineProxy();
      coffeMachineProxy.connect();
      coffeMachineProxyRegistration = bundleContext.registerService(ICoffeMachine.class, coffeMachineProxy, null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    serviceTracker = new ServiceTracker<ICoffeMachine, ServiceReference<ICoffeMachine>>(bundleContext,
        ICoffeMachine.class, null) {
      private CoffeMachineRemoteServiceDecorator decorator = null;

      @Override
      public ServiceReference<ICoffeMachine> addingService(ServiceReference<ICoffeMachine> reference) {
        ICoffeMachine coffeMachine = bundleContext.getService(reference);

        if (coffeMachine instanceof CoffeMachineProxy) {
          return null;
        }

        decorator = new CoffeMachineRemoteServiceDecorator(coffeMachine);
        decorator.connect();
        return reference;
      }

      @Override
      public void removedService(ServiceReference<ICoffeMachine> reference, ServiceReference<ICoffeMachine> service) {
        decorator.disconnect();
        decorator = null;
        context.ungetService(reference);
      }
    };
    serviceTracker.open();
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
    serviceTracker.close();
    coffeMachineProxy.disconnect();
    coffeMachineProxyRegistration.unregister();
  }

}
