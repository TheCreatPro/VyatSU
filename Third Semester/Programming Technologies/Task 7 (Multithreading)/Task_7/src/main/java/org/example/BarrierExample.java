package org.example;

import java.util.concurrent.CyclicBarrier;

public class BarrierExample {
    // Циклический барьер работает как турникет
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3); // количество, сколько нажать кнопочку
        for (int i = 0; i < 3; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток #" + w + " готовится");
                    Thread.sleep(100 + (int) (Math.random() * 3000));
                    System.out.println("Поток #" + w + " готов");
                    cb.await();
                    System.out.println("Поток #" + w + " запустился");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("done");
    }

}
