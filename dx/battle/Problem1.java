package dx.battle;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        SoldierDBMS sd = new SoldierDBMS();
        sd.init();
        sd.hire(16, 1, 5);
        sd.hire(21, 1, 5);
        System.out.println(sd.bestSoldier(1));
        sd.fire(21);
        sd.hire(25, 1, 4);
        System.out.println(sd.bestSoldier(1));
        sd.hire(30, 1, 2);
        sd.updateTeam(1, 1);
        System.out.println(sd.bestSoldier(1));
        sd.updateTeam(1, 2);
        System.out.println(sd.bestSoldier(1));
        sd.updateSoldier(30, 2);
        System.out.println(sd.bestSoldier(1));
        sd.updateTeam(1, -4);
        sd.hire(1, 1, 3);
        System.out.println(sd.bestSoldier(1));
        sd.hire(100000, 5, 1);
        System.out.println(sd.bestSoldier(5));
    }
}
