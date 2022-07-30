package dx.week4;

import java.io.*;
import java.util.*;

public class Problem5 {
    public static int T;
    public static int N;
    public static final int MAX_DEPTH = 18;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        List<List<Integer>> Tree = new ArrayList<>();
        List<Integer> search = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        long[] answers = new long[T];

        for (int t = 1; t <= T; t++) {
            Tree.clear();
            search.clear();
            queue.clear();

            N = Integer.parseInt(br.readLine());

            int[] Depths;
            int[][] Parents;

            Depths = new int[N + 1];
            Parents = new int[N + 1][MAX_DEPTH + 1];

            for (int n = 0; n <= N; n++) {
                Tree.add(new ArrayList<>());
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int index = 2;
            while (st.hasMoreTokens()) {
                int tempNode = Integer.parseInt(st.nextToken());
                Tree.get(index).add(tempNode);
                Tree.get(tempNode).add(index);
                index++;
            }

            BFS(queue, search, Tree, Depths, Parents);
            SetParents(Parents);

            long length = 0;
            for (int i = 0; i < search.size() - 1; i++) {
                int node01 = search.get(i);
                int node02 = search.get(i + 1);
                int lca = LCA(node01, node02, Depths, Parents);
                length += (Depths[node01] - Depths[lca] + Depths[node02] - Depths[lca]);
            }
            answers[t - 1] = length;
        }

        for (int i = 0; i < T; i++) {
            bw.write("#" + (i + 1) + " " + answers[i] + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static void BFS(Queue<Integer> queue, List<Integer> result, List<List<Integer>> Tree, int[] Depths,
                                    int[][] Parents) {
        boolean[] visits = new boolean[N + 1];
        queue.offer(1);

        while (!queue.isEmpty()) {
            int visitNode = queue.poll();
            visits[visitNode] = true;
            result.add(visitNode);

            int adjacencySize = Tree.get(visitNode).size();

            for (int i = 0; i < adjacencySize; i++) {
                int adjacencyNode = Tree.get(visitNode).get(i);
                if (!visits[adjacencyNode]) {
                    queue.offer(adjacencyNode);
                    Parents[adjacencyNode][0] = visitNode;
                    Depths[adjacencyNode] = Depths[visitNode] + 1;
                }
            }
        }
    }

    public static void SetParents(int[][] Parents) {
        for (int i = 1; i <= MAX_DEPTH; i++) {
            for (int n = 1; n <= N; n++) {
                int halfParent = Parents[n][i - 1];
                Parents[n][i] = Parents[halfParent][i - 1];
            }
        }
    }

    public static int LCA(int nodeA, int nodeB, int[] Depths, int[][] Parents) {
        if (Depths[nodeA] < Depths[nodeB]) {
            int temp = nodeA;
            nodeA = nodeB;
            nodeB = temp;
        }

        for (int i = MAX_DEPTH; i >= 0; i--) {
            long difference = Depths[nodeA] - Depths[nodeB];
            if (difference >= (1L << i)) {
                nodeA = Parents[nodeA][i];
            }
        }

        if (nodeA == nodeB) {
            return nodeA;
        }

        for (int i = MAX_DEPTH; i >= 0; i--) {
            if (Parents[nodeA][i] != Parents[nodeB][i]) {
                nodeA = Parents[nodeA][i];
                nodeB = Parents[nodeB][i];
            }
        }

        return Parents[nodeA][0];
    }
}
