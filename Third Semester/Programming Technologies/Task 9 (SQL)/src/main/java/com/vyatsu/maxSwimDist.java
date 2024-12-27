package com.vyatsu;

public enum maxSwimDist {
    SHORT(10, "Короткая"), MEDIUM(100, "Средняя"), LONG(1000, "Длинная");

    private final int length;
    private final String description;

    maxSwimDist(int length, String description) {
        this.length = length;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}