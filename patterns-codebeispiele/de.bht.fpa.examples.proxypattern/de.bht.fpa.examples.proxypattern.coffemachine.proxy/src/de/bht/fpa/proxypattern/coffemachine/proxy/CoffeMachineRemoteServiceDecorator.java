package de.bht.fpa.proxypattern.coffemachine.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import de.bht.fpa.proxypattern.coffemachine.ICoffeMachine;

/**
 * Opens a Java Socket and expects following serialized objects:
 * <table border="1">
 * <tr>
 * <td>IN</td>
 * <td>OUT</td>
 * </tr>
 * <tr>
 * <td>insertChip</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>ecjectChip</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>selectBeverage</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>dispenseBeverage</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>getState</td>
 * <td>String</td>
 * </tr>
 * <tr>
 * <td>getCapacity</td>
 * <td>Integer</td>
 * </tr>
 * <tr>
 * <td>getLocation</td>
 * <td>String</td>
 * </tr>
 * <tr>
 * <td>setCapacity, Integer</td>
 * <td></td>
 * </tr>
 * </table>
 * 
 * setState is not supported
 * 
 * @author Siamak Haschemi
 * 
 */
public class CoffeMachineRemoteServiceDecorator {

  private final ICoffeMachine coffeMachine;

  private ServerSocket s = null;

  public CoffeMachineRemoteServiceDecorator(ICoffeMachine coffeMachine) {
    this.coffeMachine = coffeMachine;
  }

  public synchronized void connect() {
    new Thread() {
      @Override
      public void run() {
        try {
          s = new ServerSocket(12345);
          while (s != null) {
            System.out.println(getClass().getName() + " Waiting for connections ...");
            handleClient(s.accept());
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }

  public synchronized void disconnect() {
    try {
      s.close();
      s = null;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleClient(final Socket client) {
    System.out.println(getClass().getName() + " got connection: " + client.getInetAddress());
    new Thread() {
      @Override
      public void run() {
        try {
          ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
          ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
          while (client.isConnected()) {
            handleCommand(ois, oos);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start();
  }

  private void handleCommand(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
    String command;
    try {
      command = (String) ois.readObject();
    } catch (Exception e) {
      return;
    }
    System.out.println(getClass().getName() + " Processing command: " + command);

    if (command.equals("insertChip")) {
      oos.writeObject(coffeMachine.insertChip());
    } else if (command.equals("ecjectChip")) {
      oos.writeObject(coffeMachine.ecjectChip());
    } else if (command.equals("selectBeverage")) {
      oos.writeObject(coffeMachine.selectBeverage());
    } else if (command.equals("dispenseBeverage")) {
      oos.writeObject(coffeMachine.dispenseBeverage());
    } else if (command.equals("getState")) {
      oos.writeObject(coffeMachine.getState());
    } else if (command.equals("getCapacity")) {
      oos.writeObject(coffeMachine.getCapacity());
    } else if (command.equals("getLocation")) {
      oos.writeObject(coffeMachine.getLocation());
    } else if (command.equals("setCapacity")) {
      coffeMachine.setCapacity((Integer) ois.readObject());
    }
    oos.flush();
  };

}
