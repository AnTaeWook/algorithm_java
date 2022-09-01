package dx.week13;

import java.util.Scanner;

public class Problem5 {
    static int[][] board;
    static boolean[][] visited;
    static int maximum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];
        maximum = -1;

        for (int i = 0; i < testCaseCount; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            board = new int[row][col];
            visited = new boolean[row][col];

            for (int j = 0; j < row; j++) {
                String string = scanner.next();
                for (int k = 0; k < col; k++) {
                    board[j][k] = string.charAt(k) == '.' ? 0 : 1;
                }
            }
            visited[0][0] = true;
            dfs(0, 0, 0);
            answers[i] = maximum;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static void dfs(int x, int y, int dist) {
        if ((x + 1 >= board.length || board[x + 1][y] == 1) && (y + 1 >= board[0].length || board[x][y + 1] == 1)) {
            maximum = Math.max(maximum, dist);
        }
        if (x + 1 < board.length && board[x + 1][y] == 0 && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            dfs(x + 1, y, dist + 1);
            visited[x + 1][y] = false;
        }
        if (y + 1 < board[0].length && board[x][y + 1] == 0 && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            dfs(x, y + 1, dist + 1);
            visited[x][y + 1] = false;
        }
    }
}
