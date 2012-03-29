package de.bht.fpa.examples.frameworks.v2.ownframework;

import org.apache.commons.validator.routines.EmailValidator;

import de.bht.fpa.examples.frameworks.Person;

public class UseOurOwnInputFramework {
  private static String name;
  private static String email;

  public static void main(String[] args) throws Exception {
    InputFramework inputFramework = new InputFramework();

    inputFramework.addInput("What is your name? ", new IInputListener() {
      @Override
      public boolean onInput(String input) {
        name = input;
        return true;
      }
    });

    inputFramework.addInput("What is your E-Mail? ", new IInputListener() {
      @Override
      public boolean onInput(String input) {
        email = input;
        return emailIsValid(email);
      }
    });

    inputFramework.setFinishListener(new IFinishListener() {
      @Override
      public void onFinish() {
        Person person = new Person(name, email);
        System.out.println(person);
      }
    });

    inputFramework.start();
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
