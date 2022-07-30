package dx.week5;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int itemCount = scanner.nextInt();
            int knapsackLimit = scanner.nextInt();
            int[] itemWeights = new int[itemCount + 1];
            int[] itemPrices = new int[itemCount + 1];
            int[][] dp = new int[itemCount + 1][knapsackLimit + 1];

            for (int j = 1; j < itemCount + 1; j++) {
                itemWeights[j] = scanner.nextInt();
                itemPrices[j] = scanner.nextInt();
            }
            for (int j = 0; j < itemCount + 1; j++) {
                for (int k = 0; k < knapsackLimit + 1; k++) {
                    if (j == 0 || k == 0) {
                        dp[j][k] = 0;
                        continue;
                    }
                    if (itemWeights[j] <= k) {
                        dp[j][k] = Math.max(dp[j - 1][k], dp[j - 1][k - itemWeights[j]] + itemPrices[j]);
                        continue;
                    }
                    dp[j][k] = dp[j - 1][k];
                }
            }
            answers[i] = dp[itemCount][knapsackLimit];
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
