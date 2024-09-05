public class Okun extends Fish {
  public static int count = 0;
  private static final int size = 34;

  public Okun(String name, int age, int maxSwimDist) {
    super(name, age, maxSwimDist, size);
    count++;
  }
}