package com.vyatsu;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static final int CARS_COUNT = 6;
    static CountDownLatch countDownLatchStart = new CountDownLatch(CARS_COUNT);
    static CountDownLatch countDownLatchEnd = new CountDownLatch(CARS_COUNT);
    static ArrayList<String> winners = new ArrayList<>();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race, 2000 + (int) (Math.random() * 10), countDownLatchStart, countDownLatchEnd);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        countDownLatchStart.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        countDownLatchEnd.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        // вывод победителей
        System.out.println("Победители:");
        for (int i = 0; i < Car.atomicCounter.get(); i++) {
            System.out.println((i + 1) + " место: " + winners.get(i));
        }
    }
}