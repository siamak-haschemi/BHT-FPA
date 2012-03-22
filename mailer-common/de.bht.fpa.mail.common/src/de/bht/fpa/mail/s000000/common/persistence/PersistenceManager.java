/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class allows access to the Java Persistence API (JPA) based database for
 * the mail client. It provides global access to an {@link EntityManager} by
 * being a singleton.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class PersistenceManager {
  private static final PersistenceManager INSTANCE = new PersistenceManager();

  private PersistenceManager() {

  }

  public static PersistenceManager getInstance() {
    return INSTANCE;
  }

  public EntityManagerFactory createEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("fpa");
  }
}
