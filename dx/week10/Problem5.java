package dx.week10;

import java.util.Scanner;
import java.util.TreeSet;

public class Problem5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] cows;
        TreeSet<Integer> horses;

        for (int i = 0; i < testCaseCount; i++) {
            int cowCount = scanner.nextInt();
            int horseCount = scanner.nextInt();
            int gap = Math.abs(scanner.nextInt() - scanner.nextInt());
            cows = new int[cowCount];
            horses = new TreeSet<>();
            int minimum = Integer.MAX_VALUE, count = 0;

            for (int j = 0; j < cowCount; j++) {
                cows[j] = scanner.nextInt();
            }
            for (int j = 0; j < horseCount; j++) {
                horses.add(scanner.nextInt());
            }
            for (int cow : cows) {
                if (horses.contains(cow)) {
                    if (gap < minimum) {
                        minimum = gap;
                        count = 1;
                    } else if (gap == minimum) {
                        count += 1;
                    }
                    continue;
                }
                if (horses.lower(cow) != null) {
                    int lowerDist = Math.abs(cow - horses.lower(cow)) + gap;
                    if (lowerDist < minimum) {
                        minimum = lowerDist;
                        count = 1;
                    } else if (lowerDist == minimum) {
                        count += 1;
                    }
                }
                if (horses.higher(cow) != null) {
                    int higherDist = Math.abs(cow - horses.higher(cow)) + gap;
                    if (higherDist < minimum) {
                        minimum = higherDist;
                        count = 1;
                    } else if (higherDist == minimum) {
                        count += 1;
                    }
                }
            }
            System.out.println("#" + (i + 1) + " " + minimum + " " + count);
        }
    }
}
