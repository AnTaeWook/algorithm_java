package dx.week10;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int studyDayCount = scanner.nextInt();
            int cheatCount = scanner.nextInt();
            int maximum = cheatCount + 1;
            int[] dayGaps = new int[studyDayCount - 1];

            int d1 = scanner.nextInt();
            for (int j = 0; j < studyDayCount - 1; j++) {
                int d2 = scanner.nextInt();
                dayGaps[j] = d2 - d1 - 1;
                d1 = d2;
            }
            int start = 0, end = 0, size = 0;
            while (end < studyDayCount - 1) {
                size += dayGaps[end];
                if (size > cheatCount) {
                    maximum = Math.max(maximum, end - start + 1 + cheatCount);
                    if (size < dayGaps[end]) {
                        start = end + 1;
                    } else {
                        while (size > cheatCount) {
                            size -= dayGaps[start++];
                        }
                    }
                }
                end++;
            }
            answers[i] = Math.max(maximum, end - start + 1 + cheatCount);
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
