package de.bht.fpa.mail.s000000.common.model;

import java.io.Serializable;

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
    return getId().equals(test.getId());
  }

  @Override
  public int compareTo(BaseEntity other) {
    return getId().compareTo(other.getId());
  }

}