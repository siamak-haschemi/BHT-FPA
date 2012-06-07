/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.imapsync;

public class SynchronizationException extends Exception {
  private static final long serialVersionUID = 1L;

  public SynchronizationException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
