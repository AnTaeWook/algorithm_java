package dx.week7;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            String original = scanner.next();
            String pattern = scanner.next();
            int matchCount = 0;
            int[] matchTable = new int[pattern.length()];

            int patternIndex = 0;
            for (int j = 1; j < pattern.length(); j++) {
                while (patternIndex > 0 && pattern.charAt(patternIndex) != pattern.charAt(j)) {
                    patternIndex = matchTable[patternIndex - 1];
                }
                if (pattern.charAt(patternIndex) == pattern.charAt(j)) {
                    patternIndex++;
                    matchTable[j] = patternIndex;
                }
            }
            patternIndex = 0;
            for (int j = 0; j < original.length(); j++) {
                while (patternIndex > 0 && pattern.charAt(patternIndex) != original.charAt(j)) {
                    patternIndex = matchTable[patternIndex - 1];
                }
                if (pattern.charAt(patternIndex) == original.charAt(j)) {
                    if (patternIndex == pattern.length() - 1) {
                        matchCount += 1;
                        patternIndex = matchTable[patternIndex];
                    } else {
                        patternIndex++;
                    }
                }
            }
            answers[i] = matchCount;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
