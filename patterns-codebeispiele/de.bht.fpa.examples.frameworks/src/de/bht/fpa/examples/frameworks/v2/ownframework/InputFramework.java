package de.bht.fpa.examples.frameworks.v2.ownframework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class InputFramework {
  private final List<Input> inputs = new LinkedList<Input>();
  private IFinishListener finishListener;

  public void addInput(String question, IInputListener inputListener) {
    inputs.add(new Input(question, inputListener));
  }

  public void setFinishListener(IFinishListener finishListener) {
    this.finishListener = finishListener;
  }

  public void start() throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    for (Input input : inputs) {
      String result = "";
      do {
        System.out.print(input.getQuestion());
        result = reader.readLine();
      } while (!input.getInputListener().onInput(result));
    }

    reader.close();

    if (finishListener != null) {
      finishListener.onFinish();
    }
  }
}
