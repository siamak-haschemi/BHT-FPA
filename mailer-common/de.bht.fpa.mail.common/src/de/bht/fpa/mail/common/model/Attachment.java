package de.bht.fpa.mail.common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Attachment extends BaseEntity {
  private static final long serialVersionUID = 7886155285127229440L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fileName;

  @Lob
  private String body;

  @Override
  @XmlElement
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("[Attachment: ");
    s.append("id=").append(id).append(" ");
    s.append("fileName=").append(fileName).append(" ");
    s.append("body=").append(body);
    s.append("]");
    return s.toString();
  }

}
