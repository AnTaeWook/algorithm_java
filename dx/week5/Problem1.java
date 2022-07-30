package dx.week5;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        int[][] dp;
        String string01, string02;

        for (int i = 0; i < testCaseCount; i++) {
            string01 = scanner.next();
            string02 = scanner.next();
            dp = new int[string01.length() + 1][string02.length() + 1];

            for (int j = 0; j < string01.length() + 1; j++) {
                for (int k = 0; k < string02.length() + 1; k++) {
                    if (j == 0 || k == 0) {
                        dp[j][k] = 0;
                        continue;
                    }
                    if (string01.charAt(j - 1) == string02.charAt(k - 1)) {
                        dp[j][k] = dp[j - 1][k - 1] + 1;
                        continue;
                    }
                    dp[j][k] = Math.max(dp[j - 1][k], dp[j][k - 1]);
                }
            }
            answers[i] = dp[string01.length()][string02.length()];
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
