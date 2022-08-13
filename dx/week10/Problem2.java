package dx.week10;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        long[] answers = new long[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            long candleCount = scanner.nextLong();
            long rootCount = (long) Math.sqrt(candleCount * 8 + 1);
            answers[i] = rootCount * rootCount != candleCount * 8 + 1 || (rootCount - 1) % 2 == 1 ? -1 : (rootCount - 1) / 2;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
