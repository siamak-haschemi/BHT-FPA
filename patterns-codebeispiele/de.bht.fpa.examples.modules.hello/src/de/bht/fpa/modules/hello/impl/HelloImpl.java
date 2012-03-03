package de.bht.fpa.modules.hello.impl;

import de.bht.fpa.modules.hello.IHello;

public class HelloImpl implements IHello {
  private final String m_hello;

  public HelloImpl(String hello) {
    m_hello = hello;
  }

  @Override
  public String sayHello() {
    return "Hello " + m_hello;
  }
}
