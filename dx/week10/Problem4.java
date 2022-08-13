package dx.week10;

import java.util.Scanner;
import java.util.TreeSet;

class Peak implements Comparable<Peak> {
    int start;
    int period;
    int index;

    public Peak(int start, int period, int index) {
        this.start = start;
        this.period = period;
        this.index = index;
    }

    @Override
    public int compareTo(Peak peak) {
        if (start == peak.start) {
            return period - peak.period;
        }
        return start - peak.start;
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        int maximum;

        for (int i = 0; i < testCaseCount; i++) {
            int myCommercialTime = scanner.nextInt();
            int peakCount = scanner.nextInt();
            int[] peakStart = new int[peakCount];
            int[] accumulate = new int[peakCount + 1];
            TreeSet<Peak> treeSet = new TreeSet<>();
            maximum = 0;

            int sum = 0;
            for (int j = 0; j < peakCount; j++) {
                peakStart[j] = scanner.nextInt();
                int peakEnd = scanner.nextInt();
                int period = peakEnd - peakStart[j];
                treeSet.add(new Peak(peakStart[j], period, j));
                sum += period;
                accumulate[j + 1] = sum;
            }

            for (int j = 0; j < peakCount; j++) {
                int end = peakStart[j] + myCommercialTime;
                Peak peak = treeSet.lower(new Peak(end, 0, 0));
                maximum = Math.max(maximum, accumulate[peak.index] - accumulate[j] + Math.min(end - peak.start, peak.period));
            }
            answers[i] = maximum;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
