package dx.week8;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pos implements Comparable<Pos> {
    int x;
    int y;
    int cost;
    int value;

    public Pos(int x, int y, int cost, int value) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.value = value;
    }

    @Override
    public int compareTo(Pos target) {
        return this.value < target.value ? 1 : -1;
    }
}

public class Problem3 {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int size = scanner.nextInt();
            int[][] board = new int[size][size];
            int[][] myBoard = new int[size][size];

            for (int[] arr : board) {
                String[] input = scanner.next().split("");
                for (int j = 0; j < size; j++) {
                    arr[j] = Integer.parseInt(input[j]);
                }
            }
            for (int[] arr : myBoard) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }
            bfs(board, myBoard, 0, 0);
            answers[i] = myBoard[size - 1][size - 1];
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static void bfs(int[][] board, int[][] myBoard, int x, int y) {
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.offer(new Pos(x, y, 0, 0));
        myBoard[x][y] = 0;
        Pos temp;
        int nx, ny;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                nx = temp.x + dx[i];
                ny = temp.y + dy[i];
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) {
                    continue;
                }
                if (temp.cost + board[nx][ny] < myBoard[nx][ny]) {
                    queue.offer(new Pos(nx, ny, temp.cost + board[nx][ny], board[nx][ny]));
                    myBoard[nx][ny] = temp.cost + board[nx][ny];
                }
            }
        }
    }
}
