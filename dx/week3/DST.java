package dx.week3;

import java.util.ArrayDeque;
import java.util.Deque;

class DNode {
    String data;
    DNode[] nodeList;

    public DNode(String data) {
        this.data = data;
        nodeList = new DNode[30];
    }

    public void addSubNode(DNode dNode) {
        for (int i = 0; i < nodeList.length; i++) {
            if (nodeList[i] == null) {
                nodeList[i] = dNode;
                return;
            }
        }
    }

    public int findIndexWithData(String data) {
        for (int i = 0; i < nodeList.length; i++) {
            if (nodeList[i] == null) {
                continue;
            }
            if (nodeList[i].data.equals(data)) {
                return i;
            }
        }
        return -1;
    }
}

public class DST {
    private DNode root;

    private String charArrToString(char[] charArr) {
        StringBuilder str = new StringBuilder();
        for (char c : charArr) {
            if (c == '\0') {
                break;
            }
            str.append(c);
        }
        return str.toString();
    }

    private DNode getNode(String data) {
        return new DNode(data);
    }

    void init(int n) {
        root = getNode("/");
    }

    void showAsBFS() {
        Deque<DNode> queue = new ArrayDeque<>();
        queue.offer(root);
        DNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.println(temp.data);
            for (DNode dNode : temp.nodeList) {
                if (dNode != null) {
                    queue.offer(dNode);
                }
            }
        }
        System.out.println();
    }

    void cmd_mkdir(char[] path, char[] name) {
        String strPath = charArrToString(path);
        String strName = charArrToString(name);
        DNode temp = findNodeWithPath(strPath);

        if (temp == null) {
            return;
        }
        temp.addSubNode(getNode(strName));
    }

    void cmd_rm(char[] path) {
        String strPath = charArrToString(path);
        String[] directories = strPath.split("/");
        String target = directories[directories.length - 1];
        DNode temp = findEveNodeWIthPath(strPath);
        int indexForRemove;

        if (temp == null) {
            return;
        }
        indexForRemove = temp.findIndexWithData(target);
        temp.nodeList[indexForRemove] = null;
    }

    void cmd_cp(char[] srcPath, char[] dstPath) {
        String strSrcPath = charArrToString(srcPath);
        String strDstPath = charArrToString(dstPath);
        DNode temp;

        DNode srcNode = findNodeWithPath(strSrcPath);
        DNode dstNode = findNodeWithPath(strDstPath);

        if (srcNode == null || dstNode == null) {
            return;
        }
        temp = getNode(srcNode.data);
        dstNode.addSubNode(temp);
        recCopy(srcNode, temp);
    }

    void cmd_mv(char[] srcPath, char[] dstPath) {
        String strSrcPath = charArrToString(srcPath);
        String strDstPath = charArrToString(dstPath);
        String[] directories = strSrcPath.split("/");
        String target = directories[directories.length - 1];
        DNode srcNode = findEveNodeWIthPath(strSrcPath);
        DNode dstNode = findNodeWithPath(strDstPath);
        int nodeIndex;

        if (srcNode == null || dstNode == null) {
            return;
        }
        nodeIndex = srcNode.findIndexWithData(target);
        dstNode.addSubNode(srcNode.nodeList[nodeIndex]);
        srcNode.nodeList[nodeIndex] = null;
    }

    int cmd_find(char[] path) {
        String strPath = charArrToString(path);
        DNode temp = findNodeWithPath(strPath);
        if (temp == null) {
            return -1;
        }
        return recGetDirectoryCount(temp) - 1;
    }

    private DNode findNodeWithPath(String path) {
        DNode temp = root;
        String[] directories = path.split("/");
        int directoryIdx = 1;
        int nodeIndex;

        if (directories.length <= 0) {
            return root;
        }
        while (directoryIdx < directories.length) {
            nodeIndex = temp.findIndexWithData(directories[directoryIdx]);
            temp = temp.nodeList[nodeIndex];
            directoryIdx++;
        }
        return temp;
    }

    private DNode findEveNodeWIthPath(String path) {
        DNode temp = root;
        String[] directories = path.split("/");
        int directoryIdx = 1;
        int nodeIndex;

        if (directories.length <= 0) {
            return root;
        }
        while (directoryIdx < directories.length - 1) {
            nodeIndex = temp.findIndexWithData(directories[directoryIdx]);
            temp = temp.nodeList[nodeIndex];
            directoryIdx++;
        }
        return temp;
    }

    private void recCopy(DNode srcNode, DNode dstNode) {
        DNode temp;

        for (int i = 0; i < srcNode.nodeList.length; i++) {
            if (srcNode.nodeList[i] != null) {
                temp = getNode(srcNode.nodeList[i].data);
                dstNode.addSubNode(temp);
                recCopy(srcNode.nodeList[i], temp);
            }
        }
    }

    private int recGetDirectoryCount(DNode dNode) {
        int count = 1;
        for (DNode temp : dNode.nodeList) {
            if (temp != null) {
                count += recGetDirectoryCount(temp);
            }
        }
        return count;
    }
}
