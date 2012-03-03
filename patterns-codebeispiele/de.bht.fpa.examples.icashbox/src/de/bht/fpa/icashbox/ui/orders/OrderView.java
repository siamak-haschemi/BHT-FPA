package de.bht.fpa.icashbox.ui.orders;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import de.bht.fpa.icashbox.mvp.PassiveViewPart;

public class OrderView extends PassiveViewPart {
  public static final String ID = "de.bht.fpa.icashbox.view";

  private Composite parent;
  private Button addProductBtn;
  private Button addExtraBtn;
  private TreeViewer orderTreeViewer;
  private ComboViewer productsComboViewer;
  private ComboViewer extrasComboViewer;

  private TreeViewerColumn treeViewerProductColumn;

  private TreeViewerColumn treeViewerPriceColumn;

  public OrderView() {
  }

  public Button getAddProductBtn() {
    return addProductBtn;
  }

  public Button getAddExtraBtn() {
    return addExtraBtn;
  }

  public TreeViewer getOrderTreeViewer() {
    return orderTreeViewer;
  }

  public ComboViewer getProductsComboViewer() {
    return productsComboViewer;
  }

  public ComboViewer getExtrasComboViewer() {
    return extrasComboViewer;
  }

  public TreeViewerColumn getTreeViewerProductColumn() {
    return treeViewerProductColumn;
  }

  public TreeViewerColumn getTreeViewerPriceColumn() {
    return treeViewerPriceColumn;
  }

  /**
   * This is a callback that will allow us to create the viewer and initialize
   * it.
   */
  @Override
  public void basicCreatePartControl(Composite parent) {
    this.parent = parent;
    parent.setLayout(new GridLayout(2, false));

    productsComboViewer = new ComboViewer(parent, SWT.READ_ONLY);
    Combo productsCombo = productsComboViewer.getCombo();
    productsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    addProductBtn = new Button(parent, SWT.NONE);
    addProductBtn.setText("+");

    extrasComboViewer = new ComboViewer(parent, SWT.READ_ONLY);
    Combo extrasCombo = extrasComboViewer.getCombo();
    extrasCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    addExtraBtn = new Button(parent, SWT.NONE);
    addExtraBtn.setText("+");

    orderTreeViewer = new TreeViewer(parent, SWT.BORDER);
    orderTreeViewer.setUseHashlookup(true);

    Tree orderTree = orderTreeViewer.getTree();
    orderTree.setHeaderVisible(true);
    orderTree.setLinesVisible(true);
    orderTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

    treeViewerProductColumn = new TreeViewerColumn(orderTreeViewer, SWT.NONE);
    TreeColumn trclmnProduct = treeViewerProductColumn.getColumn();
    trclmnProduct.setWidth(100);
    trclmnProduct.setText("Product");

    treeViewerPriceColumn = new TreeViewerColumn(orderTreeViewer, SWT.NONE);
    TreeColumn trclmnPrice = treeViewerPriceColumn.getColumn();
    trclmnPrice.setWidth(100);
    trclmnPrice.setText("Price");
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    parent.setFocus();
  }

}