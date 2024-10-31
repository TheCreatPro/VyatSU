package com.vyatsu;

import java.util.ArrayList;

public class Box<T extends Fruit> {  // джейнерик
    protected ArrayList<T> box = new ArrayList<>();

    public Box(T... fruits) {
        for (T fruit : fruits) {
            box.add(fruit);
        }
    }

    public ArrayList<T> getBox() {
        return box;
    }

    public void add(T fruit) {
        box.add(fruit);
    }

    public void add(int count) {
        try {
            for (int i = 0; i < count; i++) {
                box.add((T) box.get(0).getClass().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public float getWeight() {
        float result = 0.0F;
        for (T fruit : box) {
            result += fruit.getFruitWeight();
        }
        return result;
    }

    public boolean compare(Box<?> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    // Пересыпать только если anotherBox того же типа
    public void pourBox(Box<T> anotherBox) {
        if (anotherBox.getBox() == this.box) { return; }
        anotherBox.getBox().addAll(this.box);
        this.box.clear();
    }

    public void printBox() {
        if (box.isEmpty()) {
            System.out.println("Пустая коробка");
        } else {
            for (T fruit : box) {
                System.out.println(fruit);
            }
        }
    }
}
