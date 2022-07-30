package dx.week4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class GPos {
    int x;
    int y;

    public GPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Problem4 {
    private static int[][] board;
    private static int boardSize;
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = {0, -1, -1, -1, 0, 1,  1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        int[] answers = new int[caseCount];
        String dataset;
        int clickCount;

        for (int i = 0; i < caseCount; i++) {
            boardSize = scanner.nextInt();
            board = new int[boardSize][boardSize];
            scanner.nextLine();
            clickCount = 0;

            for (int j = 0; j < boardSize; j++) {
                dataset = scanner.next();
                for (int k = 0; k < boardSize; k++) {
                    if (dataset.charAt(k) == '*') {
                        board[j][k] = 2;
                    }
                }
            }

            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (board[j][k] == 2) {
                        marking(j, k, 0, 1);
                    }
                }
            }

            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (board[j][k] == 0) {
                        bfs(j, k);
                        clickCount += 1;
                    }
                }
            }

            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (board[j][k] == 1) {
                        clickCount += 1;
                    }
                }
            }
            answers[i] = clickCount;
        }

        for (int i = 0; i < caseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    static void marking(int x, int y, int from, int to) {
        int nx, ny;
        for (int i = 0; i < 8; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                continue;
            }
            if (board[nx][ny] == from) {
                board[nx][ny] = to;
            }
        }
    }

    static void bfs(int x, int y) {
        int nx, ny;
        Deque<GPos> queue = new ArrayDeque<>();
        board[x][y] = 3;

        queue.offer(new GPos(x, y));
        while (!queue.isEmpty()) {
            GPos gPos = queue.poll();
            marking(gPos.x, gPos.y, 1, 3);
            for (int i = 0; i < 8; i++) {
                nx = gPos.x + dx[i];
                ny = gPos.y + dy[i];
                if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                    continue;
                }
                if (board[nx][ny] == 0) {
                    board[nx][ny] = 3;
                    queue.offer(new GPos(nx, ny));
                }
            }
        }
    }
}
