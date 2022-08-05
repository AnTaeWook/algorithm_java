package dx.week8;

class Node {
    int data;
    int id;

    public Node(int id, int data) {
        this.id = id;
        this.data = data;
    }
}

public class Problem1 {
    private static final int MAX_NODE = 1000001;
    private Node[] nodes;
    private int nodeCount;

    public void init() {
        nodes = new Node[MAX_NODE];
        nodeCount = 0;
    }

    public void addUser(int uID, int income) {
        makeNode(uID, income);
        Node temp;
        for (int i = nodeCount; i/2 > 0 && nodes[i/2].data <= nodes[i].data; i >>= 1) {
            if (nodes[i].data == nodes[i / 2].data && nodes[i].id > nodes[i / 2].id) {
                break;
            }
            temp = nodes[i];
            nodes[i] = nodes[i/2];
            nodes[i/2] = temp;
        }
    }

    private void makeNode(int uID, int data) {
        nodes[++nodeCount] = new Node(uID, data);
    }

    int getTop10(int[] result) {
        int limit = Math.min(nodeCount, 10);
        Node[] nodeArr = new Node[limit];
        for (int i = 1; i <= limit; i++) {
            nodeArr[i - 1] = pop();
            result[i - 1] = nodeArr[i - 1].id;
        }
        for (int i = 1; i <= limit; i++) {
            addUser(nodeArr[i - 1].id, nodeArr[i - 1].data);
        }
        return limit;
    }

    private int top() {
        if (nodeCount < 1) {
            return 0;
        }
        return nodes[1].data;
    }

    Node pop() {
        if (nodeCount < 1) {
            return null;
        }
        Node returnValue = nodes[1];
        nodes[1] = nodes[nodeCount--];
        int nodeToMove;
        Node temp;
        for (int i = 1; i * 2 <= nodeCount;) {
            if (i * 2 == nodeCount) {
                nodeToMove = i * 2;
            }
            else{
                if (nodes[i * 2].data == nodes[i * 2 + 1].data) {
                    nodeToMove = nodes[i*2].id < nodes[i*2 + 1].id ? i*2 : i*2 + 1;
                } else {
                    nodeToMove = nodes[i*2].data > nodes[i*2 + 1].data ? i*2 : i*2 + 1;
                }
            }
            if (nodes[i].data < nodes[nodeToMove].data ||
                    (nodes[i].data == nodes[nodeToMove].data && nodes[i].id > nodes[nodeToMove].id) ) {
                temp = nodes[i];
                nodes[i] = nodes[nodeToMove];
                nodes[nodeToMove] = temp;
                i = nodeToMove;
            } else {
                break;
            }
        }
        return returnValue;
    }

    void show() {
        for (int i = 1; i <= nodeCount; i++) {
            System.out.println(nodes[i].id + " " + nodes[i].data);
        }
        System.out.println();
    }
}
