package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.icashbox.model.Order;

public class OrderTreeContentProvider implements ITreeContentProvider {
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof Order) {
      return ((Order) parentElement).getProducts();
    }
    return new Object[0];
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }
}