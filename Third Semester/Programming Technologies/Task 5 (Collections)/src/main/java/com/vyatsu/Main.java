package com.vyatsu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] array1 = {"Стол", "Тетрадь", "Ручка", "Телефон", "Стол", "Стул", "Стол", "Тетрадь", "Ножницы", "Компьютер", "Компьютер"};
        Map<String, Integer> map = new HashMap<>();
        for (String s : array1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        System.out.println(map.entrySet());
        Schedule rasp = new Schedule();
        rasp.add("Среда", "Событие 5");
        rasp.add("Воскресенье", "Событие 7");

        rasp.add("Понедельник", "Событие 1");
        rasp.add("Понедельник", "Событие 2");
        rasp.add("Вторник", "Событие 3");
        rasp.add("Среда", "Событие 4");

        rasp.add("Среда", "Событие 7");
        rasp.add("Понедельник", "Событие 1");
        rasp.print();
    }
}
