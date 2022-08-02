package dx.week7;

import java.util.HashMap;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int len1 = scanner.nextInt();
            int len2 = scanner.nextInt();
            int count = 0;
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < len1; j++) {
                hashMap.put(scanner.next(), 1);
            }
            for (int j = 0; j < len2; j++) {
                if (hashMap.containsKey(scanner.next())) {
                    count++;
                }
            }
            answers[i] = count;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
