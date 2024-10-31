package com.vyatsu;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Задание 1:
        String[] arrayStr1 = {"a", "b", "c"};
        swap(arrayStr1, 0, 1);
        System.out.println(Arrays.stream(arrayStr1).toList());
        // Задание 2:
        System.out.println(convertToArrayList(arrayStr1));

        // Задание 3:
        Box<Apple> box_of_apples = new Box<>(new Apple());
        for (int i = 0; i < 4; i++) {
            box_of_apples.add(4);
        }
        System.out.println("Вес коробок с яблоками: " + box_of_apples.getWeight()); // Задание 3.d

        Box<Orange> box_of_oranges = new Box<>();
//        for (int i = 0; i < 3; i++) {
//            box_of_oranges.add(new Orange());
//        }
        System.out.println("Вес коробок с апельсинами: " + box_of_oranges.getWeight());
        System.out.println(box_of_oranges.compare(box_of_apples));  // Задание 3.e


        // Задание 3.f
        Box<Apple> box_of_apples1 = new Box<>();
        //box_of_apples1.add(new Apple());
        System.out.println("Коробка1 до пересыпания: ");
        //box_of_apples1.printBox();
        box_of_apples.pourBox(box_of_apples1);  // из текущей в другую
        System.out.println("Коробка1 после пересыпания: ");
        //box_of_apples1.printBox();  // box_of_apples1 будет наполнена
        System.out.println("Коробка0 после пересыпания: ");
        //box_of_apples.printBox();  // box_of_apples будет пустая

        // Индивидуальное задание:
        Box<Orange> box_of_oranges_individual = new Box<>(new Orange(), new Orange());
        System.out.println("ИНДИВИДУАЛЬНОЕ ЗАДАНИЕ\n1:");
        //box_of_oranges_individual.printBox();
        box_of_oranges_individual.add(3);
        System.out.println("2:");
        //box_of_oranges_individual.printBox();


        System.out.println("3:");
        BoxOfLemon box_of_lemon_individual = new BoxOfLemon(new Lemon(), new Lemon());
        BoxOfLemon box_of_fruit_individual2 = new BoxOfLemon(new Lemon(), new Lemon(), new Orange());
        box_of_fruit_individual2.add(3);
        System.out.println("box_of_lemon_individual до пересыпания:");
        box_of_lemon_individual.printBox();
        System.out.println("box_of_fruit_individual2 до пересыпания:");
        box_of_fruit_individual2.printBox();
        // box_of_lemon_individual.pourBox(box_of_fruit_individual2);  // из lemon в fruit
        box_of_lemon_individual.pourBox(box_of_fruit_individual2);  // из lemon в fruit
        System.out.println("box_of_lemon_individual после пересыпания:");
        box_of_lemon_individual.printBox();
        System.out.println("box_of_fruit_individual2 после пересыпания:");
        box_of_fruit_individual2.printBox();


        System.out.println("#############################");
        BoxOfLemon<Lemon> boxL = new BoxOfLemon<>(new Lemon(), new Lemon());
        BoxOfLemon<Lemon> boxL1 = new BoxOfLemon<Lemon>(new Lemon(), new Lemon());
        Box<Apple> boxA = new Box<Apple>(new Apple(), new Apple());
        Box<Apple> boxA1 = new Box<Apple>(new Apple(), new Apple());
        boxA1.printBox();
        // boxA1.pourBox(boxL);  // должна быть ошибка
        boxA.printBox();
    }

    private static void swap(Object[] inputArray, int index1, int index2) {  // свап объектов массива
        Object temp = inputArray[index1];
        inputArray[index1] = inputArray[index2];
        inputArray[index2] = temp;
    }
    private static <T> List<T> convertToArrayList(T[] inputArray) {  // Преобразует массив в список
        return Arrays.asList(inputArray);
    }
}
