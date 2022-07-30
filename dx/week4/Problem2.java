package dx.week4;

class Pos {
    int x;
    int y;
    int d;

    public Pos(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Problem2 {
    int[][] board;
    boolean[][] visited;
    Pos[] queue;
    int head;
    int tail;

    void bfs_init(int map_size, int map[][]) {
        board = new int[map_size][map_size];
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                board[i][j] = map[i][j];
            }
        }
    }

    int bfs(int x1, int y1, int x2, int y2) {
        visited = new boolean[board.length][board.length];
        visited[y1 - 1][x1 - 1] = true;
        queue = new Pos[board.length * board.length];
        head = 0;
        tail = 0;
        queue[head++] = new Pos(x1 - 1, y1 - 1, 0);
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int nx, ny;

        while (tail < head) {
            Pos p = queue[tail++];
            if (p.x == x2 - 1 && p.y == y2 - 1) {
                return p.d;
            }
            for (int i = 0; i < 4; i++) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) {
                    continue;
                }
                if (board[ny][nx] <= 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue[head++] = new Pos(nx, ny, p.d + 1);
                }
            }
        }
        return -1;
    }
}
