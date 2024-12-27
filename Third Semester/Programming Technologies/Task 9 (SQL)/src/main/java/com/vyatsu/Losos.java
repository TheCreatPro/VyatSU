package com.vyatsu;

@Table(title = "Losos1")
public class Losos {
    @Column
    int id;

    @Column
    String name;

    @Column
    int age;

    @Column
    float fishMaxRunDist;

    @Column
    maxSwimDist maxSwimDist;

    public Losos(int id, String name, int age, float fishMaxRunDist, maxSwimDist maxSwimDist) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.fishMaxRunDist = fishMaxRunDist;
        this.maxSwimDist = maxSwimDist;
    }
}
