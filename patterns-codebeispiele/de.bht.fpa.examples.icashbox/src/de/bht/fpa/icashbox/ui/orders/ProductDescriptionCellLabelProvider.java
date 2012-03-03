package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import de.bht.fpa.icashbox.model.Product;

public final class ProductDescriptionCellLabelProvider extends CellLabelProvider {
  @Override
  public void update(ViewerCell cell) {
    Product product = (Product) cell.getElement();
    cell.setText(product.getDescription());
  }
}