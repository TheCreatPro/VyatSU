package com.vyatsu;

public class Robot implements Actions, SuperJump {
    String name;
    int maxDist;  // Длина, которую может пробежать робот
    int maxHeight;  // Высота, на которую может запрыгнуть робот

    public Robot(String name, int maxDist, int maxHeight) {
        this.name = name;
        this.maxDist = maxDist;
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return this.name;
    }

    public boolean run(int dist) {
        return dist <= maxDist;
//        if (dist <= maxDist) {
//            System.out.printf("Робот модели '%s' пробежал %s м.\n", name, dist);
//            return true;
//        } else {
//            System.out.printf("Робот модели '%s' не смог пробежать %s м. (т.к. его максимальная дистанция %s м.).\n", name, dist, maxDist);
//            return false;
//        }
    }


    public boolean jump(int height) {
        return height <= maxHeight;
//        if (height <= maxHeight) {
//            System.out.printf("Робот модели '%s' перепрыгнул препятствие высотой %s м.\n", name, height);
//            return true;
//        } else {
//            System.out.printf("Робот модели '%s' не смог перепрыгнуть препятствие высотой %s м. (т.к. его максимальная высота прыжка %s м.).\n", name, height, maxHeight);
//            return false;
//        }
    }

    public boolean superJump(int height) {
        //System.out.println("Робот использует супер прыжок и перепрыгивает стену высотой " + height + " м.");
        return true;
    }
}
