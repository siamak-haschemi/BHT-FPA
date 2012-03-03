package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;

import com.google.inject.Inject;

import de.bht.fpa.icashbox.model.Coffee;
import de.bht.fpa.icashbox.model.Milk;
import de.bht.fpa.icashbox.model.Order;
import de.bht.fpa.icashbox.model.Sugar;
import de.bht.fpa.icashbox.model.Tea;
import de.bht.fpa.icashbox.mvp.BasePresenter;

public class OrderPresenter extends BasePresenter<OrderView, Order> {
  @Inject
  private OrderTreeContentProvider orderTreeContentProvider;

  @Inject
  private AddProductBtnSelection addProductBtnSelection;

  @Inject
  private AddExtrasBtnSelection addExtrasBtnSelection;

  @Inject
  private ProductDescriptionCellLabelProvider productDescriptionCellLabelProvider;

  @Inject
  private ProductPriceCellLabelProvider productPriceCellLabelProvider;

  @Inject
  private ClassLabelProvider classLabelProvider;

  @SuppressWarnings("rawtypes")
  @Override
  protected void uiAvailable(OrderView view) {
    view.getAddProductBtn().addSelectionListener(addProductBtnSelection);
    view.getAddExtraBtn().addSelectionListener(addExtrasBtnSelection);

    view.getOrderTreeViewer().setContentProvider(orderTreeContentProvider);
    view.getTreeViewerProductColumn().setLabelProvider(productDescriptionCellLabelProvider);
    view.getTreeViewerPriceColumn().setLabelProvider(productPriceCellLabelProvider);

    view.getProductsComboViewer().setContentProvider(ArrayContentProvider.getInstance());
    view.getProductsComboViewer().setLabelProvider(classLabelProvider);
    Class[] products = new Class[] { Coffee.class, Tea.class };
    view.getProductsComboViewer().setInput(products);
    view.getProductsComboViewer().setSelection(new StructuredSelection(products[0]));

    view.getExtrasComboViewer().setContentProvider(ArrayContentProvider.getInstance());
    view.getExtrasComboViewer().setLabelProvider(classLabelProvider);
    Class[] extras = new Class[] { Milk.class, Sugar.class };
    view.getExtrasComboViewer().setInput(extras);
    view.getExtrasComboViewer().setSelection(new StructuredSelection(extras[0]));

    getView().getOrderTreeViewer().setInput(getModel());
  }

  @Override
  protected void uiDispose(OrderView view) {
    view.getAddProductBtn().removeSelectionListener(addProductBtnSelection);
    view.getAddExtraBtn().removeSelectionListener(addExtrasBtnSelection);
  }

}
