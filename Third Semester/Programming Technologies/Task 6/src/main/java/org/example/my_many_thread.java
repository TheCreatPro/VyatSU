package org.example;

public class my_many_thread {
    public static void main(String[] args) {
//        new Thread(new Runnable() {  // серое - значит лишнее. можно заменить на лямбда функцию
//            @Override
//            public void run() {
//                System.out.println("0");
//            }
//        }).start();
        // потоки отрабатывают непоследовательно, порядок не гарантирован
        new Thread(() -> {
            System.out.println("0");
            // цикл внутри потока можно использовать
            // Можно ещё дописать команд в функцию
        }).start();

        new Thread(() -> {
            System.out.println("1");
        }).start();

        new Thread(() -> {
            System.out.println("3");
        }).start();
        System.out.println("#############");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t1-" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t2-" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        try {
            System.out.println("1");
            t1.join();  // join - оба потока ждут друг друга
            System.out.println("2");
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// проверяю сколько мне создать доп потоков.