package dx.week13;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        int[] table;

        for (int i = 0; i < testCaseCount; i++) {
            String string = scanner.next();
            table = new int[string.length()];
            if (string.length() < 3) {
                answers[i] = string.length() < 2 || string.charAt(0) == string.charAt(1) ? 1 : 2;
                continue;
            }
            table[0] = 1;
            table[1] = string.charAt(0) == string.charAt(1) ? 1 : 2;
            table[2] = string.charAt(0) == string.charAt(1) || string.charAt(1) == string.charAt(2) ? 2 : 3;
            for (int j = 3; j < string.length(); j++) {
                if (string.charAt(j - 1) == string.charAt(j)) {
                    table[j] = table[j - 3] + 2;
                } else {
                    table[j] = table[j - 1] + 1;
                }
            }
            answers[i] = table[string.length() - 1];
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
