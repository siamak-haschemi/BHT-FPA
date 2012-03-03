package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.google.inject.Inject;

import de.bht.fpa.icashbox.model.Milk;
import de.bht.fpa.icashbox.model.Order;
import de.bht.fpa.icashbox.model.Product;
import de.bht.fpa.icashbox.model.Sugar;

public final class AddExtrasBtnSelection extends SelectionAdapter {
  @Inject
  private Order model;

  @Inject
  private OrderView orderView;

  @SuppressWarnings("unchecked")
  @Override
  public void widgetSelected(SelectionEvent e) {
    Product product = null;

    ISelection treeSelection = orderView.getOrderTreeViewer().getSelection();
    if (treeSelection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) treeSelection;
      product = (Product) structuredSelection.getFirstElement();
    }

    if (product != null) {
      IStructuredSelection selection = (IStructuredSelection) orderView.getExtrasComboViewer().getSelection();
      Class<Product> p = (Class<Product>) selection.getFirstElement();

      if (p.equals(Milk.class)) {
        model.replaceProduct(product, new Milk(product));
        orderView.getOrderTreeViewer().setInput(model);
      } else if (p.equals(Sugar.class)) {
        model.replaceProduct(product, new Sugar(product));
        orderView.getOrderTreeViewer().setInput(model);
      }
    }
  }
}