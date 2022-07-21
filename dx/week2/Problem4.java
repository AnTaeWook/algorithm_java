package dx.week2;

import java.util.Scanner;

class mNode {
    public String data;
    public mNode next;

    public mNode(String data) {
        this.data = data;
        this.next = null;
    }
}

class myNode {
    private final static int MAX_NODE = 10000;

    private mNode[] node = new mNode[MAX_NODE];
    private int nodeCnt = 0;
    private mNode head;

    private mNode getNode(String data) {
        node[nodeCnt] = new mNode(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode("HEAD");
    }

    public void addToTail(String data){
        mNode temp = head;
        while(true){
            if(temp.next == null){
                temp.next = getNode(data);
                break;
            }
            temp = temp.next;
        }
    }

    public void create(String[] dataset){
        for(String data : dataset){
            addToTail(data);
        }
    }

    public void add(int num, String data) {
        int count = 0;
        mNode temp = head;
        mNode newNode = getNode(data);
        while(true) {
            if(count == num){
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            }
            temp = temp.next;
            count++;
        }
    }

    public void delete(int num) {
        int count = 0;
        mNode temp = head;
        while(true) {
            if(count == num){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
            count++;
        }
    }

    public void change(int num, String data) {
        int count = 0;
        mNode temp = head;
        while(true) {
            if(count == num + 1){
                temp.data = data;
                break;
            }
            temp = temp.next;
            count++;
        }
    }

    public void run(String[] order){
        switch (order[0]) {
            case "I": add(Integer.parseInt(order[1]), order[2]);
                break;
            case "D": delete(Integer.parseInt(order[1]));
                break;
            case "C": change(Integer.parseInt(order[1]), order[2]);
        }
    }

    public String get(int num){
        int count = 0;
        mNode temp = head;
        while(true) {
            if(temp == null){
                return "-1";
            }
            if(count == num + 1){
                return temp.data;
            }
            temp = temp.next;
            count++;
        }
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        myNode mn = new myNode();
        String[] dataSet;

        int T = scanner.nextInt();
        scanner.nextLine();
        String[] answers = new String[T];
        int m, l;

        for(int testCase = 0; testCase < T; testCase++){
            mn.init();
            dataSet = scanner.nextLine().split(" ");
            m = Integer.parseInt(dataSet[1]);
            l = Integer.parseInt(dataSet[2]);

            mn.create(scanner.nextLine().split(" "));
            for(int i = 0; i < m; i++){
                mn.run(scanner.nextLine().split(" "));
            }
            answers[testCase] = mn.get(l);
        }

        for(int i = 0; i < answers.length; i++){
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
