package com.vyatsu;

public class Contest {
    private static int superJumpsCount = 2;  // Общее количество супер прыжков для всех участников

    public static boolean runContest(Actions participant, int dist) {
        if (participant.run(dist)) {
            System.out.printf("'%s' пробежал %s м.\n", participant.getName(), dist);
            return true;
        } else {
            System.out.printf("'%s' не смог пробежать %s м.\n", participant.getName(), dist);
            System.out.printf("'%s' выбывает.\n\n", participant.getName());
            return false;
        }

    }

    public static boolean jumpContest(Actions participant, int height) {
        if (participant.jump(height)) {
            System.out.printf("'%s' перепрыгнул стену высотой %s м.\n", participant.getName(), height);
            return true;
        }

        System.out.printf("'%s' не смог перепрыгнуть стену высотой %s м. ", participant.getName(), height);
        if (useSuperJump() && ((SuperJump) participant).superJump(height)) {
            System.out.printf("'%s' использует супер прыжок и перепрыгивает стену высотой %s м.\n", participant.getName(), height);
            return true;
        }  // с 24 по 28 строку лучше всего обернуть в try catch

        System.out.printf("'%s' выбывает.\n\n", participant.getName());
        return false;
    }

    private static boolean useSuperJump() {
        if (superJumpsCount > 0) {
            superJumpsCount--;
            System.out.print("Использован супер прыжок! Осталось попыток: " + superJumpsCount + ". ");
            return true;
        } else {
            System.out.println("Супер прыжки закончились.");
            return false;
        }
    }
}
