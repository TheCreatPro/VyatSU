package com.vyatsu;

public enum Treadmill implements Obstacle {
    SHORT("Короткая дистанция", 100), MEDIUM("Средняя дистанция", 500), LONG("Длинная дистанция", 1000);

    private final int length;

    Treadmill(String name, int length) {
        this.length = length;
    }

    public boolean step(Actions obj) {
        return Contest.runContest(obj, length);
    }
}
