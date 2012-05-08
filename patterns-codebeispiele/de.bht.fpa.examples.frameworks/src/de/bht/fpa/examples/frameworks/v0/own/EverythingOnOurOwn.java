package de.bht.fpa.examples.frameworks.v0.own;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.bht.fpa.examples.frameworks.Person;

public class EverythingOnOurOwn {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("What is your name? ");
    String name = reader.readLine();

    String email = "";
    do {
      System.out.print("What is your E-Mail address? ");
      email = reader.readLine();
    } while (!emailIsValid(email));

    Person person = new Person(name, email);
    System.out.println(person);

    reader.close();
  }

  private static boolean emailIsValid(String email) {
    boolean valid = email != null && email.trim().length() > 0 && email.contains("@");
    if (!valid) {
      System.out.println("E-Mail is not valid!");
    }
    return valid;
  }
}
