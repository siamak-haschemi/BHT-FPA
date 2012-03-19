/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.testdata;

import java.util.Collection;

import de.bht.fpa.mail.s000000.common.model.Message;

/**
 * This class provides its user with a number of {@link Message} objects for
 * testing purposes. <br/>
 * <br/>
 * For example, to generate 20 {@link Message}s, do: <br/>
 * {@code MessageTestDataProvider provider = new MessageTestDataProvider();}<br/>
 * {@code provider.setTestDataProvider(...);}<br/>
 * {@code List<Message> messages = provider.getMessages(20);}
 * 
 * @author Frank Schmidt
 * @author Siamak Haschem
 */
public final class MessageTestDataProvider {
  private ITestDataProvider testDataProvider;

  public void setTestDataProvider(ITestDataProvider testDataProvider) {
    this.testDataProvider = testDataProvider;
  }

  /**
   * Asks the {@link ITestDataProvider} for a number of {@link Message} objects
   * and returns them as a {@link List}.
   * 
   * @return a {@link List} containing the {@link Message} objects
   */
  public Collection<Message> getMessages() {
    if (testDataProvider == null) {
      throw new IllegalStateException("Cannot provide test data. You need to set a TestDataProvider first.");
    }
    return testDataProvider.getMessages();
  }
}
