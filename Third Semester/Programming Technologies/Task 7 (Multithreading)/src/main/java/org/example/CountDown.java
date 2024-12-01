package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) {
        // только после того как 6 отщёлкнуло тогда делаем

        // барьер сколько раз нажали - тогда и стартуем
        // отличия: защёлки действуют один раз, барьер можно перезапускать. В одном проекте лучше не мешать
        final int THREADS_COUNT = 6;
        final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);
        System.out.println("Начинаем");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(200 * w + (int) (Math.random() * 500));
                    countDownLatch.countDown();
                    System.out.println("Поток #" + w + " - готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Работа завершена");
    }
}
