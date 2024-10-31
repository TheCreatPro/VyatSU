package com.vyatsu;

import java.util.ArrayList;

public class BoxOfLemon<T extends Fruit> extends Box<Fruit> {
    private ArrayList<Fruit> boxLemon = new ArrayList<Fruit>();

    public BoxOfLemon(Fruit... fruits) {  // конструктор принимает любые фрукты
        for (Fruit f : fruits) {
            boxLemon.add(f);
        }
    }

    @Override
    public ArrayList<Fruit> getBox() {
        return boxLemon;
    }

    @Override
    public void add(Fruit fruit) {
        boxLemon.add(fruit);
    }

    public void add(int count) {  // добавление определенного количества лимонов
        for (int i = 0; i < count; i++) {
            try {
                this.boxLemon.add(this.boxLemon.get(0).getClass().getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Метод пересыпания для коробок с лимонами. Принимает только другую коробку с лимонами
    public void pourBox(BoxOfLemon<Lemon> anotherBox) {
        anotherBox.getBox().addAll(this.boxLemon);
        this.boxLemon.clear();
    }

    public void printBox() {
        if (this.boxLemon.isEmpty()) {
            System.out.println("Пустая коробка");
        } else {
            for (Fruit fruit : this.boxLemon) {
                System.out.println(fruit);
            }
        }
    }
}
