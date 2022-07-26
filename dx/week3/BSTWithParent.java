package dx.week3;

import java.util.ArrayList;
import java.util.Objects;

class pNode {
    int data;
    pNode left;
    pNode right;
    pNode parent;

    public pNode(int data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }
}

public class BSTWithParent {
    private final static int MAX_NODE = 10001;

    private pNode[] node = new pNode[MAX_NODE];
    private pNode root;

    public void initWithIndex(int totalCountOfNode) {
        for(int i = 1; i <= totalCountOfNode; i++) {
            node[i] = new pNode(i);
        }
        root = node[1];
    }

    public void add(int[] dataset) {
        for(int i = 0; i < dataset.length; i += 2) {
            if(node[dataset[i]].left == null) {
                node[dataset[i]].left = node[dataset[i + 1]];
                node[dataset[i + 1]].parent = node[dataset[i]];
                continue;
            }
            node[dataset[i]].right = node[dataset[i + 1]];
            node[dataset[i + 1]].parent = node[dataset[i]];
        }
    }

    public String getStringWithInOrder() {
        if(root == null) {
            return "";
        }
        return recGetStringWithInOrder("", root);
    }

    public String recGetStringWithInOrder(String initString, pNode temp) {
        if(temp.left != null) {
            initString += recGetStringWithInOrder("", temp.left);
        }
        initString += " " + temp.data;
        if(temp.right != null) {
            initString += recGetStringWithInOrder("", temp.right);
        }
        return initString;
    }

    public int getLeastCommonAncestor(int firstIndex, int secondIndex) {
        ArrayList<Integer> firstAncestorArr = getAncestorArr(node[firstIndex]);
        ArrayList<Integer> secondAncestorArr = getAncestorArr(node[secondIndex]);
        int difference = Math.abs(firstAncestorArr.size() - secondAncestorArr.size());
        for(int i = 0; i < Math.min(firstAncestorArr.size(), secondAncestorArr.size()); i++) {
            if(firstAncestorArr.size() > secondAncestorArr.size()) {
                if(Objects.equals(firstAncestorArr.get(i + difference), secondAncestorArr.get(i))) {
                    return firstAncestorArr.get(i + difference);
                }
            }
            else {
                if(Objects.equals(firstAncestorArr.get(i), secondAncestorArr.get(i + difference))) {
                    return firstAncestorArr.get(i);
                }
            }
        }
        return -1;
    }

    public ArrayList<Integer> getAncestorArr(pNode base) {
        ArrayList<Integer> ancestorArr = new ArrayList<>();
        pNode temp = base;
        while(true) {
            if(temp == root) {
                break;
            }
            ancestorArr.add(temp.parent.data);
            temp = temp.parent;
        }
        return ancestorArr;
    }

    public int getSubtreeSize(int nodeIndex) {
        return recGetSubtreeSize(node[nodeIndex]);
    }

    public int recGetSubtreeSize(pNode temp) {
        int nodeCount = 0;
        if(temp.left == null && temp.right == null) {
            return 1;
        }
        if(temp.left != null) {
            nodeCount += recGetSubtreeSize(temp.left);
        }
        if(temp.right != null) {
            nodeCount += recGetSubtreeSize(temp.right);
        }
        return nodeCount + 1;
    }
}
