package dx.week2;

class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class Problem2 {
    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode(0);
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        newNode.next = head.next;
        head.next = newNode;
    }

    public void addNode2Tail(int data) {
        Node temp = head;
        while(true){
            if(temp.next == null){
                temp.next = getNode(data);
                break;
            }
            temp = temp.next;
        }
    }

    public void addNode2Num(int data, int num) {
        int count = 0;
        Node temp = head;
        Node newNode = getNode(data);
        while(true){
            if(count == num - 1){
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }
            count++;
            temp = temp.next;
        }
    }

    public void removeNode(int data) {
        Node temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.data == data){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public int getList(int[] output) {
        int count = 0;
        Node temp = head;
        while(true){
            if(temp.next == null){
                return count;
            }
            output[count] = temp.next.data;
            temp = temp.next;
            count++;
        }
    }
}