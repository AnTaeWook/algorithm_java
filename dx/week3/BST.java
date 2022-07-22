package dx.week3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data){
        this.data = data;
        left = null;
        right = null;
    }
}

public class BST {
    private final static int MAX_NODE = 10000;
    private final static String[] operators = {"+", "-", "*", "/"};

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node root;

    private Node getNode(String data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        root = null;
        nodeCnt = 0;
    }

    public void initWithIndex(int totalCountOfNode) {
        for(int i = 1; i <= totalCountOfNode; i++) {
            node[i] = new Node("");
        }
        root = node[1];
    }

    public void addForComplete(String data) {
        Node temp;
        if(root == null){
            root = getNode(data);
            return;
        }

        Deque<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        while(!nodeQueue.isEmpty()) {
            temp = nodeQueue.poll();
            if(temp.left == null) {
                temp.left = getNode(data);
                return;
            }
            else if(temp.right == null) {
                temp.right = getNode(data);
                return;
            }
            nodeQueue.offer(temp.left);
            nodeQueue.offer(temp.right);
        }
    }

    public String getStringWithInOrder() {
        if(root == null) {
            return "";
        }
        return recGetStringWithInOrder("", root);
    }

    public String recGetStringWithInOrder(String initString, Node temp) {
        if(temp.left != null) {
            initString += recGetStringWithInOrder("", temp.left);
        }
        initString += temp.data;
        if(temp.right != null) {
            initString += recGetStringWithInOrder("", temp.right);
        }
        return initString;
    }

    public int isValidFormula() {
        if(root == null) {
            return 0;
        }
        return recIsValidFormula(root);
    }

    public int recIsValidFormula(Node temp) {
        if((temp.left == null ^ temp.right == null)){
            return 0;
        }
        if(temp.left == null) {
            return Arrays.asList(operators).contains(temp.data) ? 0 : 1;
        }
        if(!Arrays.asList(operators).contains(temp.data)) {
            return 0;
        }
        return recIsValidFormula(temp.left) == 0 || recIsValidFormula(temp.right) == 0 ? 0 : 1;
    }

    public void add(String[] dataset) {
        int nodeIdx = Integer.parseInt(dataset[0]);
        node[nodeIdx].data = dataset[1];
        if(dataset.length > 2) {
            node[nodeIdx].left = node[Integer.parseInt(dataset[2])];
            node[nodeIdx].right = node[Integer.parseInt(dataset[3])];
        }
    }

    public int getResult() {
        return recGetResult(root);
    }

    public int recGetResult(Node temp) {
        if(!Arrays.asList(operators).contains(temp.data)) {
            return Integer.parseInt(temp.data);
        }
        switch (temp.data) {
            case "+":
                return recGetResult(temp.left) + recGetResult(temp.right);
            case "-":
                return recGetResult(temp.left) - recGetResult(temp.right);
            case "*":
                return recGetResult(temp.left) * recGetResult(temp.right);
            case "/":
                return recGetResult(temp.left) / recGetResult(temp.right);
            default:
                return -1;
        }
    }
}
