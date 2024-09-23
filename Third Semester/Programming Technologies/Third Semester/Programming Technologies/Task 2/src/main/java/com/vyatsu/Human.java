package com.vyatsu;

public class Human implements Actions, SuperJump {
    String name;
    int maxDist;  // Длина, которую может пробежать человек
    int maxHeight;  // Высота, на которую может перепрыгнуть человек

    public Human(String name, int maxDist, int maxHeight) {
        this.name = name;
        this.maxDist = maxDist;
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return this.name;
    }

    // @Override
    public boolean run(int dist) {
        return dist <= maxDist;
//        if (dist <= maxDist) {
//            System.out.printf("Человек с именем '%s' пробежал %s м.\n", name, dist);
//            return true;
//        } else {
//            System.out.printf("Человек с именем '%s' не смог пробежать %s м. (т.к. его максимальная дистанция %s м.)\n", name, dist, maxDist);
//            return false;
//        }
    }

    // @Override
    public boolean jump(int height) {
        return height <= maxHeight;
//        if (height <= maxHeight) {
//            System.out.printf("Человек с именем '%s' перепрыгнул препятствие высотой %s м.\n", name, height);
//            return true;
//        } else {
//            System.out.printf("Человек с именем '%s' не смог перепрыгнуть препятствие высотой %s м. (т.к. его максимальная высота прыжка %s м.).\n", name, height, maxHeight);
//            return false;
//        }
    }

    public boolean superJump(int height) {
        //System.out.println("Человек использует супер прыжок и перепрыгивает стену высотой " + height + " м.");
        return true;
    }
}
