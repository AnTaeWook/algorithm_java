package dx.week9;

import java.util.Scanner;

public class Problem3 {
    static long inversionCount;
    static int[] temp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        long[] answers = new long[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            inversionCount = 0;
            int N = scanner.nextInt();
            int[] arr = new int[N];
            temp = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = scanner.nextInt();
            }
            getInversion(arr, 0, N - 1);
            answers[i] = inversionCount;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static void getInversion(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            getInversion(arr, start, mid);
            getInversion(arr, mid + 1, end);

            int p = start;
            int q = mid + 1;
            int idx = p;

            while (p <= mid || q <= end) {
                if (q > end || p <= mid && arr[p] < arr[q]) {
                    temp[idx++] = arr[p++];
                } else {
                    if (p <= mid) {
                        inversionCount += mid - p + 1;
                    }
                    temp[idx++] = arr[q++];
                }
            }

            if (end + 1 - start >= 0) System.arraycopy(temp, start, arr, start, end + 1 - start);
        }
    }
}
