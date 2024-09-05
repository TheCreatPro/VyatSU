public class Animal {
  private String name;
  private int age;
  
  private int maxRunDist; // ограничение
  private int maxSwimDist;

  static int count = 0;

  public Animal(String name, int age, int maxRunDist, int maxSwimDist) {
    this.name = name;  
    this.age = age;
    this.maxRunDist = maxRunDist;
    this.maxSwimDist = maxSwimDist;
    count++;
  }
  
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public int getCount() {
    return count;
  }

  public void run(int dist) {
    if (dist <= this.maxRunDist) {
      System.out.println(this.name + " пробежал " + dist + " м."); //this именно для этого кота
    }
    else {
      System.out.println(this.name + " не пробежал.");
    }  
  }

  public void swim(int swimDist) {
    if (swimDist <= this.maxSwimDist) {
      System.out.println(this.name + " проплыл " + swimDist + " м.");
    }
    else {
      System.out.println(this.name + " утонул.");
    }
  }
  
}