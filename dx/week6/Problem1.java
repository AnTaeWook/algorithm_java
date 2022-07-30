package dx.week6;

import java.util.Scanner;

public class Problem1 {
    private static final int[] changes = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[][] answers = new int[testCaseCount][changes.length];

        for (int i = 0; i < testCaseCount; i++) {
            int totalChange = scanner.nextInt();
            for (int j = 0; j < 8; j++) {
                answers[i][j] = totalChange / changes[j];
                totalChange %= changes[j];
            }
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1));
            for (int j = 0; j < 8; j++) {
                System.out.print(answers[i][j] + " ");
            }
            System.out.println();
        }
    }
}
