package de.bht.fpa.mail.common;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * This class helps with handling selections on Eclipse Views.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class SelectionHelper {

  /**
   * Utility classes do not need to be instantiated.
   */
  private SelectionHelper() {
  }

  @SuppressWarnings("unchecked")
  public static <T> T handleStructuredSelection(ISelection selection, Class<T> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("clazz should not be null");
    }
    if (selection == null || !(selection instanceof IStructuredSelection)) {
      return null;
    }
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    Object firstElement = structuredSelection.getFirstElement();

    if (firstElement == null) {
      return null;
    }

    if (!clazz.isAssignableFrom(firstElement.getClass())) {
      return null;
    }
    return (T) firstElement;
  }

  public static <T> T handleStructuredSelectionEvent(SelectionChangedEvent event, Class<T> clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException("clazz should not be null");
    }
    if (event == null) {
      return null;
    }
    return handleStructuredSelection(event.getSelection(), clazz);
  }
}
