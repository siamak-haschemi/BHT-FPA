package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.LabelProvider;

public final class ClassLabelProvider extends LabelProvider {
  @SuppressWarnings("rawtypes")
  @Override
  public String getText(Object element) {
    if (element instanceof Class) {
      return ((Class) element).getSimpleName();
    } else {
      return element.getClass().getSimpleName();
    }
  }
}