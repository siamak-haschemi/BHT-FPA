package de.bht.fpa.icashbox;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import de.bht.fpa.icashbox.mvp.BasePresenter;

public class ExecutableExtensionFactory implements IExecutableExtensionFactory, IExecutableExtension {

  private IConfigurationElement config;
  private String clazzName;

  @SuppressWarnings("rawtypes")
  @Override
  public Object create() throws CoreException {
    final Bundle bundle = Activator.getDefault().getBundle();
    final Injector injector = Activator.getDefault().getInjector();

    try {
      final Class<?> clazz = bundle.loadClass(clazzName);
      final Object result = injector.getInstance(clazz);
      if (result instanceof IExecutableExtension) {
        ((IExecutableExtension) result).setInitializationData(config, null, null);
      } else if (result instanceof BasePresenter) {
        return ((BasePresenter) result).getView();
      }
      return result;
    } catch (Exception e) {
      throw new CoreException(new Status(IStatus.ERROR, bundle.getSymbolicName(), e.getMessage(), e));
    }
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    if (data instanceof String) {
      clazzName = (String) data;
    } else {
      throw new IllegalArgumentException("couldn't handle passed data : " + data); //$NON-NLS-1$
    }
    this.config = config;
  }

}
