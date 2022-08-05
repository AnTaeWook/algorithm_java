package dx.week8;

import java.util.Scanner;

class MaxHeap {
    private static final int MAX_NODE = 100001;
    private int[] nodes;
    private int nodeCount;

    public void init() {
        nodes = new int[MAX_NODE];
        nodeCount = 0;
    }

    public void add(int data) {
        nodes[++nodeCount] = data;
        int temp;
        for (int i = nodeCount; i / 2 > 0; i >>= 1) {
            if (nodes[i] > nodes[i / 2]) {
                temp = nodes[i];
                nodes[i] = nodes[i / 2];
                nodes[i / 2] = temp;
            }
        }
    }

    public int pop() {
        if (nodeCount < 1) {
            return -1;
        }

        int maxNode = nodes[1];
        int temp;
        nodes[1] = nodes[nodeCount--];
        for (int i = 1; i * 2 <= nodeCount; ) {
            int nodeToMove = i * 2 == nodeCount || nodes[i * 2] > nodes[i * 2 + 1] ? i * 2 : i * 2 + 1;
            if (nodes[i] < nodes[nodeToMove]) {
                temp = nodes[i];
                nodes[i] = nodes[nodeToMove];
                nodes[nodeToMove] = temp;
                i = nodeToMove;
            } else {
                break;
            }
        }
        return maxNode;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        String[] answers = new String[testCaseCount];
        StringBuilder sb;
        MaxHeap heap = new MaxHeap();

        for (int i = 0; i < testCaseCount; i++) {
            heap.init();
            sb = new StringBuilder();
            int orderCount = scanner.nextInt();

            for (int j = 0; j < orderCount; j++) {
                int orderType = scanner.nextInt();
                if (orderType == 1) {
                    heap.add(scanner.nextInt());
                } else {
                    sb.append(" ");
                    sb.append(heap.pop());
                }
            }
            answers[i] = sb.toString();
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1)  + answers[i]);
        }
    }
}
