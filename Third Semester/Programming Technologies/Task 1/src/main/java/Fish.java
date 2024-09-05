public abstract class Fish extends Animal {
  private static final int fishMaxRunDist = 0;
  public static int count = 0;
  public String name;
  public int size;

  public Fish(String name, int age, int maxSwimDist, int size) {
    super(name, age, fishMaxRunDist, maxSwimDist);
    this.name = name;
    this.size = size;
    count++;
  }

  public void length() {
    System.out.println("Длина " + this.name + " = " + this.size + " см.");
  }
}