package de.bht.fpa.icashbox.mvp;

import java.util.Observable;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public abstract class PassiveViewPart extends ViewPart {
  private final ObservableImpl uiAvailableObservable = new ObservableImpl();
  private final ObservableImpl uiDisposeObservable = new ObservableImpl();

  private class ObservableImpl extends Observable {
    @Override
    public synchronized void setChanged() {
      super.setChanged();
    }
  }

  public Observable getUiAvailableObservable() {
    return uiAvailableObservable;
  }

  public Observable getUiDisposeObservable() {
    return uiDisposeObservable;
  }

  @Override
  public final void createPartControl(Composite parent) {
    basicCreatePartControl(parent);

    parent.addDisposeListener(new DisposeListener() {
      @Override
      public void widgetDisposed(DisposeEvent e) {
        uiDisposeObservable.setChanged();
        uiDisposeObservable.notifyObservers();
      }
    });

    uiAvailableObservable.setChanged();
    uiAvailableObservable.notifyObservers();
  }

  @Override
  public final void dispose() {
    uiAvailableObservable.deleteObservers();
    uiDisposeObservable.deleteObservers();
    super.dispose();
  }

  protected abstract void basicCreatePartControl(Composite parent);

}
