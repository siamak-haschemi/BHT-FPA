package de.bht.fpa.proxypattern.coffemachine.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;
import de.bht.fpa.proxypattern.coffemachine.ICoffeMachineState;

public class CoffeMachineProxy implements ICoffeMachine {

  private Socket socket = null;
  private ObjectOutputStream oos = null;
  private ObjectInputStream ois = null;

  public void connect() throws Exception {
    socket = new Socket("localhost", 12345);
    oos = new ObjectOutputStream(socket.getOutputStream());
    ois = new ObjectInputStream(socket.getInputStream());
  }

  public void disconnect() throws Exception {
    if (socket != null) {
      oos.close();
      ois.close();
      socket.close();

      oos = null;
      ois = null;
      socket = null;
    }
  }

  @Override
  public String insertChip() {
    try {
      oos.writeObject("insertChip");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String ecjectChip() {
    try {
      oos.writeObject("ecjectChip");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String selectBeverage() {
    try {
      oos.writeObject("selectBeverage");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String dispenseBeverage() {
    try {
      oos.writeObject("dispenseBeverage");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setState(ICoffeMachineState state) {
    throw new UnsupportedOperationException("setState is not supported by remote proxy");
  }

  @Override
  public String getState() {
    try {
      oos.writeObject("getState");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int getCapacity() {
    try {
      oos.writeObject("getCapacity");
      oos.flush();
      return (Integer) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getLocation() {
    try {
      oos.writeObject("getLocation");
      oos.flush();
      return (String) ois.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setCapacity(int capacity) {
    try {
      oos.writeObject("setCapacity");
      oos.writeObject(capacity);
      oos.flush();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
