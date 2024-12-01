package com.vyatsu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch countDownLatchStart;
    private CountDownLatch countDownLatchEnd;
    public static AtomicInteger atomicCounter;

    static {
        CARS_COUNT = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public Car(Race race, int speed, CountDownLatch countDownLatchStart, CountDownLatch countDownLatchEnd) {
        this.race = race;
        this.speed = speed;
        this.countDownLatchStart = countDownLatchStart;
        this.countDownLatchEnd = countDownLatchEnd;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.atomicCounter = new AtomicInteger(0);
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatchStart.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            countDownLatchStart.await();  // ждём пока все будут готовы
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Stage stage : race.getStages()) {
            stage.go(this);
        }

        // добавляем финишировавшего в список победителей
        if (atomicCounter.get() < 3) {
            atomicCounter.incrementAndGet();
            Main.winners.add(this.name);
        }
        countDownLatchEnd.countDown();
    }
}

