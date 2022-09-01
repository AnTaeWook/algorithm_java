package dx.battle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

class Position {
    int x;
    int y;
    int direction;

    public Position(int y, int x, int direction) {
        this.y = y;
        this.x = x;
        this.direction = direction;
    }
}

public class Island {
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, -1, 0, 1};
    private HashSet<Position>[] islandTable;
    private int[][] board;
    private int boardSize;

    public void init(int N, int[][] mMap) {
        islandTable = new HashSet[50000];
        boardSize = N + 2;
        board = new int[N + 2][N + 2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int d = 0; d < 4; d++) {
                        int index = 4;
                        if (i + dy[d] * k < 0 || i + dy[d] * k >= N || j + dx[d] * k < 0 || j + dx[d] * k >= N) {
                            continue;
                        }
                        for (int l = 1; l < k + 1; l++) {
                            index *= 10;
                            index += mMap[i + dy[d]*l][j + dx[d]*l] - mMap[i][j] + 4;
                        }
                        if (islandTable[index] == null) {
                            islandTable[index] = new HashSet<>();
                        }
                        islandTable[index].add(new Position(i, j, d));
                    }
                }
                board[i + 1][j + 1] = mMap[i][j];
            }
        }
    }

    public int numberOfCandidate(int M, int[] mStructure) {
        if (M <= 1) {
            return (boardSize - 2) * (boardSize - 2);
        }
        int index = 4;
        StringBuilder original = new StringBuilder("" + mStructure[0]);
        StringBuilder reversed = new StringBuilder("" + mStructure[M - 1]);
        for (int i = 1; i < M; i++) {
            index *= 10;
            index += mStructure[0] - mStructure[i] + 4;
            original.append(mStructure[i]);
            reversed.append(mStructure[M - i - 1]);
        }
        if (islandTable[index] == null) {
            return 0;
        }
        return original.toString().equals(reversed.toString()) ? islandTable[index].size() / 2 : islandTable[index].size();
    }

    public int maxArea(int M, int[] mStructure, int mSeaLevel) {
        boolean[][] visited;
        int maximum = -1;
        int index = 4;
        for (int i = 1; i < M; i++) {
            index *= 10;
            index += mStructure[0] - mStructure[i] + 4;
        }
        if (islandTable[index] == null) {
            return -1;
        }
        for (Position ablePosition : islandTable[index]) {
            // build walls
            for (int i = 0; i < M; i++) {
                board[ablePosition.y + 1 + i*dy[ablePosition.direction]][ablePosition.x + 1 + i*dx[ablePosition.direction]] += mStructure[i];
            }
            // bfs
            visited = new boolean[boardSize][boardSize];
            int islandCount = boardSize * boardSize;
            Deque<Position> queue = new ArrayDeque<>();
            queue.offer(new Position(0, 0, 0));
            while (!queue.isEmpty()) {
                Position position = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = position.y + dy[i];
                    int nx = position.x + dx[i];
                    if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                        continue;
                    }
                    if (board[ny][nx] < mSeaLevel && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.offer(new Position(ny, nx, 0));
                        islandCount -= 1;
                    }
                }
            }
            maximum = Math.max(maximum, islandCount);
            // restore
            for (int i = 0; i < M; i++) {
                board[ablePosition.y + 1 + i*dy[ablePosition.direction]][ablePosition.x + 1 + i*dx[ablePosition.direction]] -= mStructure[i];
            }
        }
        return maximum;
    }
}
