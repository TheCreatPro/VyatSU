public class Losos extends Fish {
  public static int count = 0;
  private static final int size = 98;

  public Losos(String name, int age, int maxSwimDist) {
    super(name, age, maxSwimDist, size);
    count++;
  }
}