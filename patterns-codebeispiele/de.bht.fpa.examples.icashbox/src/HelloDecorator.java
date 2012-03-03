public class HelloDecorator {

  interface ICharacter {
    String print();
  }

  static class H implements ICharacter {
    @Override
    public String print() {
      return "H";
    }
  }

  static class W implements ICharacter {
    @Override
    public String print() {
      return "W";
    }
  }

  static abstract class CharacterDecorator implements ICharacter {
    private final ICharacter inner;

    public CharacterDecorator(ICharacter inner) {
      this.inner = inner;
    }

    @Override
    public String print() {
      return inner.print();
    }
  }

  static class E extends CharacterDecorator {
    public E(ICharacter inner) {
      super(inner);
    }

    @Override
    public String print() {
      return super.print() + "E";
    }
  }

  static class L extends CharacterDecorator {
    public L(ICharacter inner) {
      super(inner);
    }

    @Override
    public String print() {
      return super.print() + "L";
    }
  }

  static class O extends CharacterDecorator {
    public O(ICharacter inner) {
      super(inner);
    }

    @Override
    public String print() {
      return super.print() + "O";
    }
  }

  static class R extends CharacterDecorator {
    public R(ICharacter inner) {
      super(inner);
    }

    @Override
    public String print() {
      return super.print() + "R";
    }
  }

  static class D extends CharacterDecorator {
    public D(ICharacter inner) {
      super(inner);
    }

    @Override
    public String print() {
      return super.print() + "D";
    }
  }

  public static void main(String[] args) {
    ICharacter hello = new O(new L(new L(new E(new H()))));
    ICharacter world = new D(new L(new R(new O(new W()))));

    System.out.println(hello.print() + " " + world.print());
  }
}