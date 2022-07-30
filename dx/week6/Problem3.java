package dx.week6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem3 {
    private static int maximum, changeCount, changeCountOrigin;
    private static String[] cards;
    private static String optimal;
    private static boolean hasOverlap, overFlag;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            maximum = -1;
            hasOverlap = false; overFlag = false;
            String stringCards = scanner.next();
            cards = new String[stringCards.length()];
            for (int j = 0; j < cards.length; j++) {
                cards[j] = stringCards.substring(j, j + 1);
            }

            for (int j = 0; j < cards.length - 1; j++) {
                for (int k = j + 1; k < cards.length; k++) {
                    if (cards[j].equals(cards[k])) {
                        hasOverlap = true;
                        break;
                    }
                }
            }
            String[] forOptimal = cards.clone();
            Arrays.sort(forOptimal, Comparator.reverseOrder());
            optimal = String.join("", forOptimal);

            changeCount = scanner.nextInt();
            changeCountOrigin = changeCount;
            if (changeCount >= cards.length) {
                changeCount = cards.length - 1;
            }
            backTracking(0, 0);
            answers[i] = maximum;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static void backTracking(int start, int turn) {
        String temp;
        if (String.join("", cards).equals(optimal)) {
            overFlag = true;
            if (!hasOverlap && (changeCountOrigin - turn) % 2 == 1) {
                temp = cards[cards.length - 2]; cards[cards.length - 2] = cards[cards.length - 1]; cards[cards.length - 1] = temp;
            }
            maximum = Math.max(maximum, Integer.parseInt(String.join("", cards)));
            return;
        }
        if (turn >= changeCount) {
            maximum = Math.max(maximum, Integer.parseInt(String.join("", cards)));
            return;
        }
        for (int i = start; i < cards.length - 1; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (Integer.parseInt(cards[i]) < Integer.parseInt(cards[j])) {
                    temp = cards[i]; cards[i] = cards[j]; cards[j] = temp;
                    backTracking(i + 1, turn + 1);
                    if (overFlag) {
                        return;
                    }
                    cards[j] = cards[i]; cards[i] = temp;
                }
            }
        }
    }
}
