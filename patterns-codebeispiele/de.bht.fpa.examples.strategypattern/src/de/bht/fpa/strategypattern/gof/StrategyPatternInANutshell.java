package de.bht.fpa.strategypattern.gof;

/**
 * Shows an implementation of the GoF Strategy Pattern contained in one class.
 * 
 * @author Siamak Haschemi
 */
public class StrategyPatternInANutshell {
  public static void main(String[] args) {
    Context context = new Context();

    // configure the context with strategy A
    context.setStrategy(new ConcreteStrategyA());
    context.execute();

    // configure the context with strategy B
    context.setStrategy(new ConcreteStrategyB());
    context.execute();

    // configure the context with strategy C
    context.setStrategy(new ConcreteStrategyC());
    context.execute();

  }

  /**
   * The classes that implement a concrete strategy should implement this. The
   * context class uses this to call the concrete strategy
   * 
   * @author Siamak Haschemi
   */
  interface IStrategy {
    void execute();
  }

  /**
   * Implements the an algorithm using the strategy interface.
   * 
   * @author Siamak Haschemi
   */
  static class ConcreteStrategyA implements IStrategy {
    @Override
    public void execute() {
      System.out.println("Called ConcreteStrategyA.execute()");
    }
  }

  /**
   * Implements the an algorithm using the strategy interface.
   * 
   * @author Siamak Haschemi
   */
  static class ConcreteStrategyB implements IStrategy {
    @Override
    public void execute() {
      System.out.println("Called ConcreteStrategyB.execute()");
    }
  }

  /**
   * Implements the an algorithm using the strategy interface.
   * 
   * @author Siamak Haschemi
   */
  static class ConcreteStrategyC implements IStrategy {
    @Override
    public void execute() {
      System.out.println("Called ConcreteStrategyC.execute()");
    }
  }

  /**
   * Configured with a ConcreteStrategy object and maintains a reference to a
   * Strategy object.
   **/
  static class Context {
    private IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
      this.strategy = strategy;
    }

    public void execute() {
      strategy.execute();
    }
  }
}
