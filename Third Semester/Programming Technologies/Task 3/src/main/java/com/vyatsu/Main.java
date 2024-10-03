package com.vyatsu;

import java.util.zip.DataFormatException;

public class Main {
    static String[][] array1 = {
            {"2", "35567", "2178", "12"},
            {"52678", "1988", "12", "88889"},
            {"46889", "69", "47", "23456"},
            //{"adg", "21.1", "22", "1"},
            {"435678", "11567", "985678", "12"}
    };
    static String[][] arrayHamming = {
            {"1", "2", "3", "4"},
            {"5", "6", "8", "9"},
            {"10", "12", "15", "16"},
            {"18", "20", "24", "25"}
    };

    public static void main(String[] args) {

        check_Array(array1);
        check_Array(arrayHamming);
    }

    public static void check_Array(String[][] input_array) throws MyArraySizeException, MyArrayDataException {
        if (input_array.length != 4)
            throw new MyArraySizeException("Количество строк матрицы не соответствует 4!");

        for (String[] strings : input_array) {
            if (strings.length != 4)
                throw new MyArraySizeException("Количество столбцов матрицы не соответствует 4!");
        }
        int sum = 0, data;
        for (int i = 0; i < input_array.length; i++) {
            for (int j = 0; j < input_array[i].length; j++) {
                try {
                    data = Integer.valueOf(input_array[i][j]);
                    sum += data;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ячейка в строке " + (i + 1) + " столбце " + (j + 1) + " содержит неверный тип данных!");
                }
            }
        }

        for (int i = 0; i < input_array.length; i++) {
            for (int j = 0; j < input_array[i].length; j++) {
                // Хемминг:
                data = Integer.valueOf(input_array[i][j]);
                try {
                    if (data < 1000 && !isHammingNumber(data)) {
                        throw new MyNoHammingException("Ячейка в строке " + (i + 1) + " столбце " + (j + 1) + " не содержит число Хемминга < 1000!");
                    }
                } catch (MyNoHammingException e) {
                    e.printStackTrace();  // исключение не должно прерывать программу
                }
            }
        }
        System.out.printf("Сумма элементов матрицы: %s. ", sum);
    }

    public static boolean isHammingNumber(int num) {  // число хемминга может состоять только из произведений 2, 3, 5
        if (num <= 0) {
            return false;
        }

        // Разделяем число на множители 2, 3 и 5
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }

        // Если в конце осталась 1, то это число принадлежит последовательности Хемминга
        return num == 1;
    }
}
