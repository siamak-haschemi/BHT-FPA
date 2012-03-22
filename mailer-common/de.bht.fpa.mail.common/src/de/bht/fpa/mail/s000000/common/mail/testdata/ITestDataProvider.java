/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.testdata;

import java.util.Collection;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * An interface for all providers of {@link Message} objects. There could be
 * several implementations like random, reading form file-system, or even
 * reading from an imap server.
 * 
 * @author Siamak Haschemi
 * 
 */
public interface ITestDataProvider {
  Collection<Message> getMessages();
}
