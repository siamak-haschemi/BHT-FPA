package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.google.inject.Inject;

import de.bht.fpa.icashbox.model.Coffee;
import de.bht.fpa.icashbox.model.Order;
import de.bht.fpa.icashbox.model.Product;
import de.bht.fpa.icashbox.model.Tea;

public final class AddProductBtnSelection extends SelectionAdapter {
  @Inject
  private Order model;

  @Inject
  private OrderView orderView;

  @SuppressWarnings("unchecked")
  @Override
  public void widgetSelected(SelectionEvent e) {
    IStructuredSelection selection = (IStructuredSelection) orderView.getProductsComboViewer().getSelection();
    Class<Product> p = (Class<Product>) selection.getFirstElement();
    if (p.equals(Coffee.class)) {
      model.addProduct(new Coffee());
    } else if (p.equals(Tea.class)) {
      model.addProduct(new Tea());
    }
    orderView.getOrderTreeViewer().setInput(model);
  }
}