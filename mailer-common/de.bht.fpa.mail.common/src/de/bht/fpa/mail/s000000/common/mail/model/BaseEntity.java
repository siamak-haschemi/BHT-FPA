/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import java.io.Serializable;

/**
 * An abstract base class to collect the similarities of all our entities. Note
 * that we cannot define a primary key here, because the type of the primary
 * keys of the sub-classes is different.
 * 
 * <p>
 * <i>Note that this class can be used together with JAXB and JPA.</i>
 * </p>
 * 
 * @author Siamak Haschemi
 * 
 */
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity> {
  private static final long serialVersionUID = -2690781787087322645L;

  public abstract Long getId();

  @Override
  public int hashCode() {
    if (getId() == null) {
      return super.hashCode();
    }
    return getId().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    // object must be Test at this point
    BaseEntity test = (BaseEntity) obj;
    if (getId() != null && test.getId() != null) {
      return getId().equals(test.getId());
    }
    return false;
  }

  @Override
  public int compareTo(BaseEntity other) {
    if (getId() != null && other.getId() != null) {
      return getId().compareTo(other.getId());
    }
    return -1;
  }

}