// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;


public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    /*Cat cat = new Cat("Barsik", 5, 200);
    Cat cat1 = new Cat("Murzik", 2, 200);
    Dog dog = new Dog("Sharik", 3, 500, 200);
    Tiger tiger = new Tiger("Tigra", 4, 1000, 500);

    Shuka shuka = new Shuka("Shuka", 2, 200, 34);
    Shuka shuka1 = new Shuka("Shuka1", 3, 100, 72);
    
    Animal[] animals = {cat, cat1, dog, tiger, shuka, shuka1};  //Object[] любые объекты*/
    Animal[] animals = {new Cat("Барсик", 5, 200), new Cat("Мурзик", 2, 200), new Dog("Шарик", 3, 500, 200), new Tiger("Тигр", 4, 1000, 500), new Shuka("Щука", 2, 200), new Okun("Окунь", 2, 1000), new Losos("Лосось", 3, 1000)};
    for (Animal a : animals) {
      a.run(600);
      a.swim(10);
      System.out.println();
    }
    ((Fish) animals[4]).length();
    ((Fish) animals[5]).length();
    ((Fish) animals[6]).length();
    System.out.println("Всего животных: " + Animal.count + ". Из них рыб: " + Fish.count);
    System.out.println();
    System.out.println("Щук: " + Shuka.count);
    System.out.println("Окуней: " + Okun.count);
    System.out.println("Лососей: " + Losos.count);
    
    System.out.println("Кошек: " + Cat.count);
    System.out.println("Собак: " + Dog.count);
    System.out.println("Тигров: " + Tiger.count);

    //shuka.length();
    //cat.run(10);
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}