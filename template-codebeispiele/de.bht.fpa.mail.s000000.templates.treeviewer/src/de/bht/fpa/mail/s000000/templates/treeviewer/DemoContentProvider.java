package de.bht.fpa.mail.s000000.templates.treeviewer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DemoContentProvider implements ITreeContentProvider {

  @Override
  public void dispose() {

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

  }

  @Override
  public Object[] getChildren(Object parentElement) {
    return getElements(parentElement);
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return new Object[] { inputElement.toString() + "->Kind1", inputElement.toString() + "->Kind2" };
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    return true;
  }

}