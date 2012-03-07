package de.bht.fpa.mail.s000000.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
