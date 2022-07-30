package dx.week6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Move {
    int low;
    int high;

    public Move(int from, int to) {
        if (from > to) {
            low = (to - 1)/2;
            high = (from - 1)/2;
        } else {
            low = (from - 1)/2;
            high = (to - 1)/2;
        }
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int studentCount = scanner.nextInt();
            boolean[] isMoved = new boolean[studentCount];
            ArrayList<Move> moves = new ArrayList<>();

            for (int j = 0; j < studentCount; j++) {
                moves.add(new Move(scanner.nextInt(), scanner.nextInt()));
            }
            moves.sort(comparator);

            int movedCount = 0;
            int time = 0;
            while (movedCount < studentCount) {
                int end = -1;
                for (int j = 0; j < studentCount; j++) {
                    if (moves.get(j).low > end && !isMoved[j]) {
                        end = moves.get(j).high;
                        isMoved[j] = true;
                        movedCount++;
                    }
                }
                time++;
            }
            answers[i] = time;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    static Comparator<Move> comparator = Comparator.comparingInt(o -> o.low);
}
