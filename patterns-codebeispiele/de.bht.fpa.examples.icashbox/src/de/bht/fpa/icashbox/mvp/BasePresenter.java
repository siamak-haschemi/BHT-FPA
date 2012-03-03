package de.bht.fpa.icashbox.mvp;

import java.util.Observable;
import java.util.Observer;

import com.google.inject.Inject;

public abstract class BasePresenter<View extends PassiveViewPart, Model> {
  private Model model;
  private View view;
  private final UIObserver uiObserver = new UIObserver();

  private class UIObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
      if (o.equals(view.getUiAvailableObservable())) {
        uiAvailable(view);
      } else if (o.equals(view.getUiDisposeObservable())) {
        view.getUiAvailableObservable().deleteObserver(uiObserver);
        view.getUiDisposeObservable().deleteObserver(uiObserver);
        uiDispose(view);
      }
    }
  }

  protected void uiAvailable(View view) {

  }

  protected void uiDispose(View view) {

  }

  @Inject
  public void setView(View view) {
    this.view = view;
    view.getUiAvailableObservable().addObserver(uiObserver);
    view.getUiDisposeObservable().addObserver(uiObserver);
  }

  @Inject
  public void setModel(Model model) {
    this.model = model;
  }

  public View getView() {
    return view;
  }

  public Model getModel() {
    return model;
  }
}
