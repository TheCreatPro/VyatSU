package com.vyatsu;

import java.util.Arrays;

public class Main {
    static final int SIZE = 60_000_004;
    static final int HALF = SIZE / 2;
    static final int COUNT_THREAD = give_count_threads(SIZE);

    public static void main(String[] args) {
        // Базовое задание: с одним массивом:
        float[] first_arr = new float[SIZE];
        Arrays.fill(first_arr, 1.0f);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            first_arr[i] = (float) (first_arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения алгоритма первым методом: " + (System.currentTimeMillis() - time1));

        // с разделёнными массивами:
        float[] second_arr = new float[SIZE];
        Arrays.fill(second_arr, 1.0f);

        float[] second_arr1 = new float[HALF];
        float[] second_arr2 = new float[HALF];

        long time2 = System.currentTimeMillis();
        System.arraycopy(second_arr, 0, second_arr1, 0, HALF);
        System.arraycopy(second_arr, HALF, second_arr2, 0, HALF);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                second_arr1[i] = (float) (second_arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                second_arr2[i] = (float) (second_arr2[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) *
                        Math.cos(0.4f + (i + HALF) / 2));
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(second_arr1, 0, second_arr, 0, HALF);
        System.arraycopy(second_arr2, 0, second_arr, HALF, HALF);

        System.out.println("Время выполнения алгоритма вторым методом : " + (System.currentTimeMillis() - time2));
        System.out.println("Последнее число после первого метода : " + first_arr[SIZE - 1]);
        System.out.println("Последнее число после второго метода : " + second_arr[SIZE - 1]);  // =1.0 если размер массива нечётный
        System.out.println("Совпадают ли массивы? : " + Arrays.equals(first_arr, second_arr));

        // Индивидуальное задание:
        float[] individ_arr = new float[SIZE];
        Arrays.fill(individ_arr, 1.0f);
        long timeIndivid = System.currentTimeMillis();
        Thread[] threads = new Thread[COUNT_THREAD];
        for (int i = 0; i < COUNT_THREAD; i++) {
            int index_start = i * (SIZE / COUNT_THREAD);
            int index_end = (i + 1) * (SIZE / COUNT_THREAD);
            Thread my_thread = new Thread(() -> {
                for (int j = index_start; j < index_end; j++) {
                    individ_arr[j] = (float) (individ_arr[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) *
                            Math.cos(0.4f + j / 2));
                }
            });
            threads[i] = my_thread;
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Времени потрачено на индивидуальное задание : " + (System.currentTimeMillis() - timeIndivid));
        System.out.println("Количество потоков для индивидуального задания : " + COUNT_THREAD);
        System.out.println("Последнее число после индивидуального метода : " + individ_arr[SIZE - 1]);
        //System.out.println("Совпадают ли массивы? : " + Arrays.equals(first_arr, individ_arr));
    }

    public static int give_count_threads(int array_size) {
        int result = 3;
        while (array_size % result != 0) {
            result++;
        }
        return result;
    }
}
