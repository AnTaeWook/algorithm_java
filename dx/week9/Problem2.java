package dx.week9;

import java.util.Scanner;

public class Problem2 {
    private static long pow(int num, int p, int mod) {
        if (p <= 1) {
            return p < 1 ? 1 : num;
        }
        if (p % 2 == 0) {
            long ret = pow(num, p / 2, mod);
            return ret * ret % mod;
        } else {
            long ret = pow(num, (p - 1) / 2, mod);
            return ret * ret * num % mod;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        long[] answers = new long[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            int K = scanner.nextInt();
            long result = A * pow(2, K, A + B) % (A + B);
            answers[i] = Math.min(result, A + B - result);
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
