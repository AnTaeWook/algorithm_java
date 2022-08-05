package dx.week8;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            PriorityQueue<Integer> minimum = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> maximum = new PriorityQueue<>();
            int orderCount = scanner.nextInt();
            minimum.offer(scanner.nextInt());

            for (int j = 0; j < orderCount; j++) {
                maximum.offer(scanner.nextInt());
                minimum.offer(scanner.nextInt());
                if (minimum.peek() > maximum.peek()) {
                    minimum.offer(maximum.poll());
                    maximum.offer(minimum.poll());
                }
                answers[i] += minimum.peek();
                answers[i] %= 20171109;
            }
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
