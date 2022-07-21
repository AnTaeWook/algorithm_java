package dx.week2;

class Node2 {
    public int data;
    public Node2 next;

    public Node2(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem1 {
    private final static int MAX_NODE = 10000;

    private Node2[] node = new Node2[MAX_NODE];
    private int nodeCnt = 0;
    private Node2 head;

    public Node2 getNode2(int data) {
        node[nodeCnt] = new Node2(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode2(0);
    }

    public void addNode22Head(int data) {
        Node2 newNode2 = getNode2(data);
        newNode2.next = head.next;
        head.next = newNode2;
    }

    public void addNode22Tail(int data) {
        Node2 temp = head;
        while(true){
            if(temp.next == null){
                temp.next = getNode2(data);
                break;
            }
            temp = temp.next;
        }
    }

    public void addNode22Num(int data, int num) {
        int count = 0;
        Node2 temp = head;
        Node2 newNode2 = getNode2(data);
        while(true){
            if(count == num - 1){
                newNode2.next = temp.next;
                temp.next = newNode2;
                break;
            }
            count++;
            temp = temp.next;
        }
    }

    public void removeNode2(int data) {
        Node2 temp = head;
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
        Node2 temp = head;
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
