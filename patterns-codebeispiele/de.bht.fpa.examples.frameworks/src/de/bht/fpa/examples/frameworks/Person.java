package de.bht.fpa.examples.frameworks;

public class Person {

  private String name;
  private String email;

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String prename) {
    this.name = prename;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String phonenumber) {
    this.email = phonenumber;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Person").append("\n");
    sb.append("  ").append("name=").append(name).append("\n");
    sb.append("  ").append("email=").append(email).append("\n");
    return sb.toString();
  }

}
