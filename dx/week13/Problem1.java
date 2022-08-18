package dx.week13;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        int[][] table;
        String keyword = "SAMSUNG";

        for (int i = 0; i < testCaseCount; i++) {
            String string = scanner.next();
            table = new int[string.length() + 1][keyword.length() + 1];
            for (int j = 1; j < string.length() + 1; j++) {
                table[j][0] = 1;
            }
            for (int j = 1; j < string.length() + 1; j++) {
                for (int k = 1; k < keyword.length() + 1; k++) {
                    if (string.charAt(j - 1) == keyword.charAt(k - 1)) {
                        table[j][k] = (table[j - 1][k] + table[j][k - 1]) % 1000000007;
                    } else {
                        table[j][k] = table[j - 1][k];
                    }
                }
            }
            answers[i] = table[string.length()][keyword.length()] % 1000000007;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
