package de.bht.fpa.mail.common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Sender extends BaseEntity {
  private static final long serialVersionUID = 5371964942946641651L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;

  private String personal;

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPersonal() {
    return personal;
  }

  public void setPersonal(String personal) {
    this.personal = personal;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Sender: ");
    s.append("id=").append(id).append(" ");
    s.append("email=").append(email).append(" ");
    s.append("personal=").append(personal).append(" ");
    s.append("]");
    return s.toString();
  }

}
