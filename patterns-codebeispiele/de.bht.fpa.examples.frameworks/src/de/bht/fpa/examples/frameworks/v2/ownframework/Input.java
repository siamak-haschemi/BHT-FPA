package de.bht.fpa.examples.frameworks.v2.ownframework;

public class Input {
  private final String question;
  private final IInputListener inputListener;

  public Input(String question, IInputListener inputListener) {
    this.question = question;
    this.inputListener = inputListener;
  }

  public String getQuestion() {
    return question;
  }

  public IInputListener getInputListener() {
    return inputListener;
  }
}