package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();  // Capacity прибавляется по степени 2. если >75%, то размер увеличивается
        map.put("Ключ1", "Значение1");  // логики в складывании нет
        map.put("Ключ2", "Значение2");
        map.put("Ключ3", "Значение3");
        // System.out.println(map);  // вывод всех элементов
        System.out.println(map.get("Ключ1"));  // вывод значения ключа
        System.out.println(map.keySet());  // все ключи, которые есть
        System.out.println(map.entrySet());  // набор ключ-значение, но выводит массив[]

        for (Map.Entry<String, String> o : map.entrySet()) {
            //System.out.println(o.getKey() + " = " + o.getValue());
            System.out.println(o);
        }
        System.out.println(map.getOrDefault("A3", "Запись не найдена"));  // если запись не найдена, впиши туда значение

        // т.к. порядок не гарантируется, то
        // если нам надо так, чтобы порядок сохранялся, то используем
        // TreeHashMap - в порядке сортировке (алфавит/возрастание) LinkedHashMap - в порядке добавления
        Map<String, String> map2 = new LinkedHashMap<>();
        // к старому значению прибавляем новое:
        map.merge("Ключ3", "blabla", (oldValue, newValue) -> oldValue + newValue);
        System.out.println(map.get("Ключ3"));
        // hashtable более старый map, но разница в том что это синхронизированная коллекция

        // разбор списков разными способами
        List<String> list = new ArrayList<>(Arrays.asList("A", "A", "A", "C", "A"));
        // если будем идти через foreach, то будет ошибка, т.к. изменять коллекцию во время обхода нельзя
        // если идти в for по индексам, то будет сдвиг массива
        // остаётся правильный вариант: использование итератора. Он привязан к элементам, а не к номеру
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("A")) {
                iterator.remove();
            }
        }

        // set содержат только уникальные значения. тоже не гарантирует порядок, можно решить через LinkedHashSet(порядок добавления) и TreeSet(алфавит)
    }
}