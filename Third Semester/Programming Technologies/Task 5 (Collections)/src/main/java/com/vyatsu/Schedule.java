package com.vyatsu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Schedule {
    Map<String, ArrayList<String>> map = new LinkedHashMap<>();

    /*public Schedule() {
        map.put("Понедельник", new ArrayList());  // подчёркивает тк надо написать что в списке строки
        map.put("Вторник", new ArrayList());
        map.put("Среда", new ArrayList());
        map.put("Четверг", new ArrayList());
        map.put("Пятница", new ArrayList());
        map.put("Суббота", new ArrayList());
        map.put("Воскресенье", new ArrayList());
    }*/

    public void add(String day_of_week, String event) {  // если ключ contains, то добавляем иначе до свидания
        if (contains(event) != "None") {
            System.out.printf("Предупреждение! Событие '%s' уже назначено на %s.\n", event, contains(event));
        }
        if (!map.containsKey(day_of_week)) {
            map.put(day_of_week, new ArrayList<>());
        }
        map.get(day_of_week).add(event);
    }

    public String contains(String event) {
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getValue().contains(event)) {
                return entry.getKey();
            }
        }
        return "None";
    }

    public ArrayList get(String day_of_week) {
        return map.get(day_of_week);
    }

    public void print() {
        // System.out.println(map.entrySet());
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String day_of_week = entry.getKey();
            ArrayList<String> list = entry.getValue();
            System.out.println(day_of_week + ": " + list);
        }
    }
}
