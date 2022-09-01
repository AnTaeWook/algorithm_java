package dx.week13;

import java.util.Scanner;

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDist(Pos pos) {
        return Math.abs(x - pos.x) + Math.abs(y - pos.y);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        int[][] dp;
        Pos[] poses;

        for (int i = 0; i < testCaseCount; i++) {
            int posCount = scanner.nextInt();
            int jumpCount = scanner.nextInt();
            dp = new int[posCount + 1][jumpCount + 1];
            poses = new Pos[posCount + 1];
            for (int j = 1; j <= posCount; j++) {
                poses[j] = new Pos(scanner.nextInt(), scanner.nextInt());
            }
            answers[i] = dfs(dp, poses, 1, jumpCount);
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static int dfs(int[][] dp, Pos[] poses, int now, int leftJumpCount) {
        if (now >= dp.length - 1) {
            return dp[now][leftJumpCount] = 0;
        }
        if (dp[now][leftJumpCount] > 0) {
            return dp[now][leftJumpCount];
        }
        if (leftJumpCount == dp.length - now - 2) {
            return dp[now][leftJumpCount] = poses[now].getDist(poses[poses.length - 1]);
        }
        dp[now][leftJumpCount] = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length - now - 1 && i <= leftJumpCount; i++) {
            int next = now + i + 1;
            dp[now][leftJumpCount] = Math.min(dp[now][leftJumpCount], dfs(dp, poses, next, leftJumpCount - i) + poses[now].getDist(poses[next]));
        }
        return dp[now][leftJumpCount];
    }
}
