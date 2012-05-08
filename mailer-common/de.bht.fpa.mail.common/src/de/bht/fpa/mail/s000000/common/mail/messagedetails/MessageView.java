/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.messagedetails;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

/**
 * This JFace view shows the details of one e-mail ({@link Message}) including
 * the sender, subject, receive date, receivers, and the contents of the e-mail.<br/>
 * 
 * To use this view, you have to do two steps:
 * <ul>
 * <li>fire selection of a {@link Message} through the Eclipse Selection Service
 * </li>
 * <li>include the view somewhere in your perspective (plugin.xml)</li>
 * </ul>
 * <br/>
 * A typical plugin.xml is:
 * 
 * <pre>
 * ...
 *   &lt;extension point="org.eclipse.ui.perspectiveExtensions"&gt;  
 *     &lt;perspectiveExtension targetID="*"&gt;
 *       &lt;view
 *         closeable="false"
 *         id="de.bht.fpa.mail.s000000.common.mail.messagedetails.MessageView"
 *         minimized="false"
 *         moveable="true"
 *         ratio="0.5"
 *         relationship="stack"
 *         relative="de.bht.fpa.mail.s000000.main.perspective.bottom"
 *         showTitle="true"
 *         standalone="true"
 *         visible="true"&gt;
 *       &lt;/view&gt;
 *     &lt;/perspectiveExtension&gt;
 *   &lt;/extension&gt;
 *  ...
 * </pre>
 * 
 * @author Siamak Haschemi
 * 
 */
public class MessageView extends ViewPart {
  public MessageView() {
  }

  private FormToolkit toolkit = null;
  private Browser browserText;
  private Text txtTo;
  private Text txtReceived;
  private Text txtSubject;
  private Text txtFrom;
  private ScrolledComposite scrolledComposite;

  /**
   * Create contents of the view part.
   * 
   * @param parent
   */
  @Override
  public void createPartControl(final Composite parent) {
    toolkit = new FormToolkit(Display.getCurrent());

    scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.V_SCROLL);
    toolkit.adapt(scrolledComposite);
    scrolledComposite.setExpandHorizontal(true);
    scrolledComposite.setExpandVertical(true);
    scrolledComposite.addControlListener(new ControlAdapter() {
      @Override
      public void controlResized(ControlEvent e) {
        Rectangle r = scrolledComposite.getClientArea();
        scrolledComposite.setMinSize(parent.computeSize(r.width, SWT.DEFAULT));
      }
    });

    Composite composite = new Composite(scrolledComposite, SWT.NONE);
    toolkit.adapt(composite);
    composite.setLayout(new GridLayout(1, false));

    Composite mailHeader = new Composite(composite, SWT.NONE);
    mailHeader.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
    mailHeader.setLayout(new GridLayout(2, false));
    toolkit.adapt(mailHeader);

    Label lblFrom = new Label(mailHeader, SWT.WRAP);
    lblFrom.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
    lblFrom.setAlignment(SWT.RIGHT);
    lblFrom.setEnabled(false);
    toolkit.adapt(lblFrom, true, true);
    lblFrom.setText("Sender");

    txtFrom = new Text(mailHeader, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
    txtFrom.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
    toolkit.adapt(txtFrom, true, true);

    Label lblAbout = new Label(mailHeader, SWT.WRAP);
    lblAbout.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
    lblAbout.setAlignment(SWT.RIGHT);
    toolkit.adapt(lblAbout, true, true);
    lblAbout.setText("Subject");

    txtSubject = new Text(mailHeader, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
    txtSubject.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
    toolkit.adapt(txtSubject, true, true);

    Label lblDate = new Label(mailHeader, SWT.NONE);
    lblDate.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
    lblDate.setAlignment(SWT.RIGHT);
    toolkit.adapt(lblDate, true, true);
    lblDate.setText("Received");

    txtReceived = new Text(mailHeader, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
    txtReceived.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
    toolkit.adapt(txtReceived, true, true);

    Label lblTo = new Label(mailHeader, SWT.WRAP);
    lblTo.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
    lblTo.setAlignment(SWT.RIGHT);
    toolkit.adapt(lblTo, true, true);
    lblTo.setText("Receivers");

    txtTo = new Text(mailHeader, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
    txtTo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
    toolkit.adapt(txtTo, true, true);

    Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
    label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    label.setAlignment(SWT.CENTER);
    toolkit.adapt(label, true, true);

    browserText = new Browser(composite, SWT.WRAP);
    browserText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    toolkit.adapt(browserText, true, true);

    scrolledComposite.setContent(composite);
    scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

    getViewSite().getPage().addSelectionListener(new MessageSelectionListener(this));
  }

  @Override
  public void dispose() {
    toolkit.dispose();
    super.dispose();
  }

  @Override
  public void setFocus() {
    scrolledComposite.setFocus();
  }

  public FormToolkit getToolkit() {
    return toolkit;
  }

  public Browser getBrowserText() {
    return browserText;
  }

  public Text getTxtTo() {
    return txtTo;
  }

  public Text getTxtReceived() {
    return txtReceived;
  }

  public Text getTxtSubject() {
    return txtSubject;
  }

  public Text getTxtFrom() {
    return txtFrom;
  }

  public ScrolledComposite getScrolledComposite() {
    return scrolledComposite;
  }

}
