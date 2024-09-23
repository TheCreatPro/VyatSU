package com.vyatsu;

public class Main {
    public static void main(String[] args) {
        Actions[] participants = {
                new Human("Пётр", 1100, 2),
                new Cat("Киска Анфиска", 520, 3),
                new Robot("ОптимусПрайм1337", 300, 1)
        };
        Obstacle[] route = {
                Wall.LOW,
                Wall.HIGH,
                Wall.HIGH,
                Treadmill.SHORT,
                Treadmill.MEDIUM,
                Wall.MEDIUM,
                Treadmill.LONG,
        };
        for (Actions participant : participants) {
            for (Obstacle obstacle : route) {
                if (!obstacle.step(participant)) break;
            }
        }
    }
}
