package dx.week11;

public class Trie {
    private final int ALPHABET_SIZE = 26;
    private int deleteCount;
    private int searchCount;
    private int wordCount;
    private String word;
    private boolean isComplete;
    Node root;

    public Trie() {
        this.root = new Node();
        this.root.val = ' ';
    }

    private class Node {
        Node[] child = new Node[ALPHABET_SIZE];
        boolean isTerminal = false;
        int childNum = 0;
        int count = 0;
        char val;
    }

    private int charToInt(char c) {
        return c - 'a';
    }

    public int insert(String str) {
        Node current = root;
        for (int i = 0; i < str.length(); i++) {
            int num = this.charToInt(str.charAt(i));
            if (current.child[num] == null) {
                current.child[num] = new Node();
                current.child[num].val = str.charAt(i);
                current.childNum++;
            }
            current = current.child[num];
        }
        current.isTerminal = true;
        return ++current.count;
    }

    public int find(String str) {
        searchCount = 0;
        find(root, str, 0);
        return searchCount;
    }

    private void find(Node node, String str, int index) {
        if ((node.childNum == 0 && index != str.length()) || (index == str.length() && !node.isTerminal)) {
            return;
        }

        if (index == str.length()) {
            searchCount += node.count;
        } else {
            if (str.charAt(index) == '?') {
                for (int i = 0; i < ALPHABET_SIZE; i++) {
                    if (node.child[i] != null) {
                        find(node.child[i], str, index + 1);
                    }
                }
            } else {
                int num = charToInt(str.charAt(index));
                if (node.child[num] != null) {
                    find(node.child[num], str, index + 1);
                }
            }
        }
    }

    public int delete(String str) {
        deleteCount = 0;
        delete(root, str, 0);
        return deleteCount;
    }

    private void delete(Node node, String str, int index) {
        if ((node.childNum == 0 && index != str.length()) || (index == str.length() && !node.isTerminal)) {
            return;
        }

        if (index == str.length()) {
            node.isTerminal = false;
            deleteCount += node.count;
            node.count = 0;

            if (node.childNum == 0) {
                node = null;
            }
        } else {
            if (str.charAt(index) == '?') {
                for (int i = 0; i < ALPHABET_SIZE; i++) {
                    if (node.child[i] != null) {
                        delete(node.child[i], str, index + 1);
                        if (node.child[i] == null) {
                            node.childNum--;
                        }
                    }
                }
            } else {
                int num = charToInt(str.charAt(index));
                if (node.child[num] != null) {
                    delete(node.child[num], str, index + 1);
                    if (node.child[num] == null) {
                        node.childNum--;
                    }
                }
            }
            if (node.childNum == 0 && !node.isTerminal) {
                node = null;
            }
        }
    }

    public String findInOrder(int order) {
        Node current = root;
        wordCount = 0;
        word = "";
        isComplete = false;
        findInOrder(root, order, new StringBuilder());
        return word;
    }

    private void findInOrder(Node node, int order, StringBuilder builder) {
        Node current = node;
        StringBuilder currentBuilder = new StringBuilder(builder);

        if (!current.equals(this.root)) {
            currentBuilder.append(current.val);
        }
        if (current.isTerminal) {
            wordCount++;
            if (wordCount == order) {
                word = currentBuilder.toString();
                isComplete = true;
            }
        }
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (current.child[i] == null) {
                continue;
            }
            findInOrder(current.child[i], order, currentBuilder);
            if (isComplete) {
                break;
            }
        }
    }
}
