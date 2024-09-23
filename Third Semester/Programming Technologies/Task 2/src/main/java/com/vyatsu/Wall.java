package com.vyatsu;

public enum Wall implements Obstacle {
    LOW("Низкая стена", 1), MEDIUM("Средняя стена", 2), HIGH("Высокая стена", 30);
    private final int height;

    private Wall(String name, int height) {
        this.height = height;
    }

    public boolean step(Actions obj) {
        return Contest.jumpContest(obj, height);
    }
}
