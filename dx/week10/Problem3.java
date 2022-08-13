package dx.week10;

import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        long[] answers = new long[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int candyBagCount = scanner.nextInt();
            long candyUnit = scanner.nextLong();
            long[] candyBags = new long[candyBagCount];
            for (int j = 0; j < candyBagCount; j++) {
                candyBags[j] = scanner.nextLong();
            }

            long start = 0, end = Arrays.stream(candyBags).max().getAsLong();
            while (start != end) {
                long mid = (start + end + 1) / 2;
                long count = 0;
                for (long candyBag : candyBags) {
                    count += candyBag / mid;
                }
                if (count < candyUnit) {
                    end = mid - 1;
                } else {
                    start = mid;
                }
            }
            answers[i] = end;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
