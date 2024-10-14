package org.example;

public class DaemonExample {
    public static void main(String[] args) {
        Thread tTimer = new Thread(() -> {
            int time = 0;
            while (true) {
                try {
                    Thread.sleep((1000));
                    time++;
                    System.out.println("time " + time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /* Поток-демон работает до дех пор, пока работает основной/обычный поток
        НЕ ПРИВЯЗАНЫ к порождающему потоку
         */

        tTimer.setDaemon(true); // ПОТОК - ДЕМОН, если убрать эту строку tTimer будет работать после выполнения main
        tTimer.start();
        System.out.println("main -> sleep");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main -> end");
    }
}
