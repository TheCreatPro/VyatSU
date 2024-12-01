package com.vyatsu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Задание 1: найти наиболее часто встречающееся слово, если таких слов несколько, вывести самое короткое
        List<String> mainList = Arrays.asList("AVTOM","Барар","Барар", "Микроволновка", "Картофель", "Банан", "Банан", "AVTOM","AVTOM", "Рог");

        System.out.println("Наиболее часто встречающиеся короткие слова: " + mainList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // считаем колво частот слов
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue)) // группируем их по частоте
                .entrySet().stream()
                .max(Map.Entry.comparingByKey()) // находим самое часто встречающееся
                .map(Map.Entry::getValue) // получаем список слов с этой частотой
                .orElse(Collections.emptyList()).stream()
                .map(Map.Entry::getKey) // берём только слова
                .collect(Collectors.groupingBy(String::length)) // группируем их по длине
                .entrySet().stream()
                .min(Map.Entry.comparingByKey()) // находим самое короткое
                .map(Map.Entry::getValue) // и берём слово с минимальной длиной
                .orElse(Collections.emptyList()));
        // если таких слов нету, то вернём пустой список


        //System.out.println("Наиболее часто встречающееся короткое слово: " + mostFrequentWord);

        // Задание 2
        // создаём список объектов типа Контакт
        List<Contact> contacts = Arrays.asList(
                new Contact("Иван", "Тестов", 30, "+123456789"),
                new Contact("Петр", "Васечкин", 25, "+987654321"),
                new Contact("Сергей", "Иванов", 35, "+456789123"),
                new Contact("Анна", "Фёдоровна", 19, "+321654987")
        );

        String searchStr = "ов";  // ищем контакты, чьи фамилии содержат "ов"

        // поиск контактов, где фамилия содержит searchStr и сортировка по возрасту в порядке убывания
        System.out.println(
                contacts.stream()
                        .filter(contact -> contact.getLastName().contains(searchStr)) // фильтруем по подстроке
                        .sorted(Comparator.comparingInt(Contact::getAge).reversed()) // сортировка по убыванию возраста
                        .map(Contact::getFirstName) // и получаем только имена
                        .collect(Collectors.joining(", ", "N контактов зовут: ", ".")) // формат вывода
        );
    }
}
