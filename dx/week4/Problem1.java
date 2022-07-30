package dx.week4;

public class Problem1 {
    int[][] childList;
    int[] childCnt;
    int limit;

    public void dfs_init(int N, int[][] path) {
        childList = new int[100][5];
        childCnt = new int[100];
        for (int i = 0; i < N - 1; i++) {
            childList[path[i][0]][childCnt[path[i][0]]++] = path[i][1];
        }
    }

    public int dfs(int N) {
        limit = N;
        return rec_dfs(N);
    }

    private int rec_dfs(int N) {
        int temp = 0;
        for (int i = 0; i < childCnt[N]; i++) {
            if (childList[N][i] > limit) {
                return childList[N][i];
            }
            temp = rec_dfs(childList[N][i]);
            if (temp > 0) {
                return temp;
            }
        }
        return -1;
    }
}
