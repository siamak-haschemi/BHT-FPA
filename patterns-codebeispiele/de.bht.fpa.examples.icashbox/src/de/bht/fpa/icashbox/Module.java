package de.bht.fpa.icashbox;

import com.google.inject.AbstractModule;

import de.bht.fpa.icashbox.model.Order;
import de.bht.fpa.icashbox.ui.orders.AddExtrasBtnSelection;
import de.bht.fpa.icashbox.ui.orders.AddProductBtnSelection;
import de.bht.fpa.icashbox.ui.orders.ClassLabelProvider;
import de.bht.fpa.icashbox.ui.orders.OrderPresenter;
import de.bht.fpa.icashbox.ui.orders.OrderTreeContentProvider;
import de.bht.fpa.icashbox.ui.orders.OrderView;
import de.bht.fpa.icashbox.ui.orders.ProductDescriptionCellLabelProvider;
import de.bht.fpa.icashbox.ui.orders.ProductPriceCellLabelProvider;

/**
 * @author siamakhaschemi
 * 
 */
public class Module extends AbstractModule {
  /*
   * (non-Javadoc)
   * 
   * @see com.google.inject.AbstractModule#configure()
   */
  @Override
  protected void configure() {
    bind(Order.class).toInstance(new Order());
    bind(OrderPresenter.class).toInstance(new OrderPresenter());
    bind(OrderView.class).toInstance(new OrderView());
    bind(OrderTreeContentProvider.class).toInstance(new OrderTreeContentProvider());
    bind(AddExtrasBtnSelection.class).toInstance(new AddExtrasBtnSelection());
    bind(AddProductBtnSelection.class).toInstance(new AddProductBtnSelection());
    bind(ProductDescriptionCellLabelProvider.class).toInstance(new ProductDescriptionCellLabelProvider());
    bind(ProductPriceCellLabelProvider.class).toInstance(new ProductPriceCellLabelProvider());
    bind(ClassLabelProvider.class).toInstance(new ClassLabelProvider());
  }
}
