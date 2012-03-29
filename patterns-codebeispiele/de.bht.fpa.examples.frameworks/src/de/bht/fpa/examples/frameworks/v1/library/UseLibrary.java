package de.bht.fpa.examples.frameworks.v1.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.validator.routines.EmailValidator;

import de.bht.fpa.examples.frameworks.Person;

public class UseLibrary {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("What is your name? ");
    String name = reader.readLine();

    String email = "";
    do {
      System.out.print("What is your E-Mail address?");
      email = reader.readLine();
    } while (!emailIsValid(email));

    Person person = new Person(name, email);
    System.out.println(person);

    reader.close();
  }

  private static boolean emailIsValid(String email) {
    // use the apache commons validator library
    EmailValidator emailValidator = EmailValidator.getInstance();
    boolean valid = emailValidator.isValid(email);

    if (!valid) {
      System.out.println("E-Mail is not valid!");
    }
    return valid;
  }
}
