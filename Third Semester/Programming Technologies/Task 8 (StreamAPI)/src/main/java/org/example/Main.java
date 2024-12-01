package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

public class Main {
    public static void main(String[] args) {
        Arrays.asList("A", "S", "D").stream().forEach(System.out::println);  // для каждого (для всех) элемента распечатать
        // list.stream().mapToInt(v -> v).sum  // maptoint преобразовывать всё в инт
        //.collect(Collectors.joining("Разделитель", "Начало", "Конец");  // выведет Начало{data}Разделитель{data}Конец

        // поиск: findFirst

        // Удовлетворяют условию? .allMatch - все удовл. условию; anyMatch - хотя бы один; noneMatch - ни один не удовл.

        //
    }

}