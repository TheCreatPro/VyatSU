package org.example;

public class my_thread {  // runnable чтобы поток запускался
    public static void main(String[] args) {
        System.out.println(give_count_threads(60_000_003));
    }

    public static int give_count_threads(int array_size) {
        int result = 3;
        while (array_size % result != 0) {
            result++;
        }
        return result;
    }
}
