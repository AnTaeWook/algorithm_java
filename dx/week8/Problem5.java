package dx.week8;

import java.util.PriorityQueue;
import java.util.Scanner;

class Num implements Comparable<Num> {
    int count;
    int left;

    public Num(int count, int left) {
        this.count = count;
        this.left = left;
    }

    @Override
    public int compareTo(Num target) {
        return this.count > target.count ? 1 : -1;
    }
}

public class Problem5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int numberCount = scanner.nextInt();
            int[] nums = new int[numberCount];
            for (int j = 0; j < numberCount; j++) {
                nums[j] = scanner.nextInt();
            }
            int targetNum = scanner.nextInt();

            PriorityQueue<Num> queue = new PriorityQueue<>();
            queue.offer(new Num(0, targetNum));
            while (queue.peek().left > 0) {
                Num temp = queue.poll();
                queue.offer(new Num(temp.count + temp.left, 0));
                for (int j : nums) {
                    queue.offer(new Num(temp.count + temp.left % j, temp.left / j));
                }
            }
            answers[i] = queue.peek().count;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
