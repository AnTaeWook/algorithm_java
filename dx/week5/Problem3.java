package dx.week5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Problem3 {
    private static int[][][] dp;
    private static int[] snackAmounts;
    private static Integer[] additionalSnackAmounts;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int snackCount = scanner.nextInt();
            snackAmounts = new int[snackCount];
            for (int j = 0; j < snackCount; j++) {
                snackAmounts[j] = scanner.nextInt();
            }

            int additionalSnackCount = scanner.nextInt();
            additionalSnackAmounts = new Integer[additionalSnackCount];
            for (int j = 0; j < additionalSnackCount; j++) {
                additionalSnackAmounts[j] = scanner.nextInt();
            }
            Arrays.sort(additionalSnackAmounts, Collections.reverseOrder());
            dp = new int[snackCount + 1][additionalSnackCount + 1][2];
            fillDp(-1);

            answers[i] = takeSnack(0, 0, 1, 0);
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
        scanner.close();
    }

    private static void fillDp(int fillValue) {
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, fillValue);
            }
        }
    }

    private static int takeSnack(int currSnack, int takeCount, int isPickable, int addIndex) {
        int returnValue = 0;
        if (currSnack >= snackAmounts.length && takeCount >= additionalSnackAmounts.length) {
            return dp[currSnack][takeCount][isPickable] = 0;
        }
        if (dp[currSnack][takeCount][isPickable] > -1) {
            return dp[currSnack][takeCount][isPickable];
        }

        if (isPickable == 1) {
            if (currSnack < snackAmounts.length) {
                returnValue = Math.max(returnValue, takeSnack(currSnack + 1, takeCount, 0, addIndex) + snackAmounts[currSnack]);
                returnValue = Math.max(returnValue, takeSnack(currSnack + 1, takeCount, 1, addIndex));
            }
            if (takeCount < additionalSnackAmounts.length) {
                returnValue = Math.max(returnValue, takeSnack(currSnack, takeCount + 1, 0, addIndex + 1) + additionalSnackAmounts[addIndex]);
                returnValue = Math.max(returnValue, takeSnack(currSnack, takeCount + 1, 1, addIndex));
            }
        } else {
            if (currSnack < snackAmounts.length) {
                returnValue = Math.max(returnValue, takeSnack(currSnack + 1, takeCount, 1, addIndex));
            }
            if (takeCount < additionalSnackAmounts.length) {
                returnValue = Math.max(returnValue, takeSnack(currSnack, takeCount + 1, 1, addIndex));
            }
        }
        return dp[currSnack][takeCount][isPickable] = returnValue;
    }
}
