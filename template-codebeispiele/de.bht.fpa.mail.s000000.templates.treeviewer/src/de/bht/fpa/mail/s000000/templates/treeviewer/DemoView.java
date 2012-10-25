package de.bht.fpa.mail.s000000.templates.treeviewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public final class DemoView extends ViewPart {

  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent);
    LabelProvider l = new LabelProvider();
    DemoContentProvider d = new DemoContentProvider();
    String s = createModel();

    viewer.setLabelProvider(l);
    viewer.setContentProvider(d);
    viewer.setInput(s);
  }

  private String createModel() {
    return "Wurzel";
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
}
