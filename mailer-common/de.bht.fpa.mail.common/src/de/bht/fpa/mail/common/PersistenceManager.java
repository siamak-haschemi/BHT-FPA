package de.bht.fpa.mail.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceManager {
  private static final PersistenceManager instance = new PersistenceManager();

  private PersistenceManager() {

  }

  public static PersistenceManager getInstance() {
    return instance;
  }

  public EntityManagerFactory createEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("fpa");
  }
}
