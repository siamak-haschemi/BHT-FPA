package de.bht.fpa.modules.hello.stotter;

import de.bht.fpa.modules.hello.IHello;

public class HelloStotter implements IHello {

  private final String m_hello;

  public HelloStotter(String hello) {
    m_hello = hello;
  }

  @Override
  public String sayHello() {
    return "H-H-hhelloo " + m_hello;
  }

}
