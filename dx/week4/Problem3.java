package dx.week4;

import java.util.Scanner;

class Core {
    int x;
    int y;

    public Core(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Problem3 {
    private static int[][] board;
    private static int boardSize;
    private static Core[] cores;
    private static boolean[] isCoreToConnect;
    private static int coreCount;
    private static int minimum;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        int[] answers = new int[testCount];

        for (int t = 0; t < testCount; t++) {
            boardSize = scanner.nextInt();
            board = new int[boardSize][boardSize];
            cores = new Core[12];
            isCoreToConnect = new boolean[12];
            coreCount = 0;
            minimum = Integer.MAX_VALUE;

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (scanner.nextInt() > 0) {
                        board[i][j] = 1;
                        cores[coreCount++] = new Core(i, j);
                    }
                }
            }

            for (int i = coreCount; i > 0; i--) {
                for (int j = 0; j <= coreCount - i; j++) {
                    combinations(j, 1, i);
                }
                if (minimum < Integer.MAX_VALUE) {
                    break;
                }
            }
            answers[t] = minimum;
        }

        for (int i = 0; i < testCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static void combinations(int coreIndex, int count, int need) {
        isCoreToConnect[coreIndex] = true;
        if (count >= need) {
            dfs(0, 0);
        }
        else{
            for (int i = coreIndex + 1; i < coreCount; i++) {
                combinations(i, count + 1, need);
            }
        }
        isCoreToConnect[coreIndex] = false;
    }

    private static void dfs(int coreIndex, int dist) {
        int nx, ny;
        int count;
        boolean isConnective;
        if (coreIndex >= coreCount) {
            minimum = Math.min(minimum, dist);
            return;
        }
        if (!isCoreToConnect[coreIndex]) {
            dfs(coreIndex + 1, dist);
        }

        for (int i = 0; i < 4; i++) {
            isConnective = false;
            count = 0;
            nx = cores[coreIndex].x;
            ny = cores[coreIndex].y;

            while (true) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                    isConnective = true;
                    break;
                }
                if (board[nx][ny] > 0) {
                    break;
                }
                board[nx][ny] = 1;
                count += 1;
            }
            if (isConnective) {
                dfs(coreIndex + 1, dist + count);
            }
            for (int j = 0; j < count; j++) {
                nx -= dx[i];
                ny -= dy[i];
                board[nx][ny] = 0;
            }
        }
    }
}
