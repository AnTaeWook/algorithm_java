package dx.week2;

import java.util.Scanner;

class CNode {
    public String data;
    public CNode next;

    public CNode(String data) {
        this.data = data;
        this.next = null;
    }
}

class CodeNode {
    private final static int MAX_NODE = 10000;

    private CNode[] node = new CNode[MAX_NODE];
    private int nodeCnt = 0;
    private CNode head;

    public CNode getNode(String data){
        node[nodeCnt] = new CNode(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode("HEAD");
    }

    public void addToTail(String data){
        CNode temp = head;
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

    public void show(){
        int count = 0;
        CNode temp = head.next;
        while(true){
            if(temp == null || count >= 10){
                break;
            }
            System.out.println(temp.data);
            count++;
            temp = temp.next;
        }
    }

    public void add(int num, String[] dataset){
        CNode base = getNode(dataset[0]);
        CNode prev = base;
        CNode fin;
        CNode temp = head;
        int count = 0;

        if(dataset.length <= 1){
            while (true){
                if(count == num){
                    base.next = temp.next;
                    temp.next = base;
                    return;
                }
                temp = temp.next;
                count++;
            }
        }
        for(int i = 1; i < dataset.length; i++){
            fin = getNode(dataset[i]);
            prev.next = fin;
            prev = fin;

            if(i >= dataset.length - 1){
                while (true){
                    if(count == num){
                        fin.next = temp.next;
                        temp.next = base;
                        break;
                    }
                    temp = temp.next;
                    count++;
                }
            }
        }
    }

    public void delete(int num, int deleteCnt){
        CNode temp = head;
        CNode base;
        int count = 0, inCount = 0;
        while(true){
            if(count == num){
                base = temp;
                for(int i = 0; i < deleteCnt; i++){
                    temp = temp.next;
                }
                base.next = temp.next;
                break;
            }
            temp = temp.next;
            count++;
        }
    }

    public void run(String[] orders){
        String[] dataset;
        for(int i = 0; i < orders.length; i++){
            if(orders[i].equals("I")){
                dataset = new String[Integer.parseInt(orders[i + 2])];
                for(int j = i + 3; j < i + 3 + Integer.parseInt(orders[i + 2]); j++){
                    dataset[j - i - 3] = orders[j];
                }
                add(Integer.parseInt(orders[i + 1]), dataset);
            }
            else if(orders[i].equals("D")){
                delete(Integer.parseInt(orders[i + 1]), Integer.parseInt(orders[i + 2]));
            }
            else if(orders[i].equals("A")){
                for(int j = i + 2; j < i + 2 + Integer.parseInt(orders[i + 1]); j++){
                    addToTail(orders[j]);
                }
            }
        }
    }

    public String get(){
        String first10Codes = "";
        int count = 0;
        CNode temp = head.next;
        while(true){
            if(temp == null || count >= 10){
                break;
            }
            first10Codes += temp.data + " ";
            count++;
            temp = temp.next;
        }
        return first10Codes.trim();
    }
}

public class Problem3 {
    static String[] answers = new String[10];

    public static void main(String[] args) {
        int n, m;
        CodeNode cn = new CodeNode();
        Scanner scanner = new Scanner(System.in);
        for(int testCase = 0; testCase < 10; testCase++){
            cn.init();
            n = scanner.nextInt();
            scanner.nextLine();
            String[] codes = scanner.nextLine().split(" ");
            cn.create(codes);

            m = scanner.nextInt();
            scanner.nextLine();
            String[] orders = scanner.nextLine().split(" ");
            cn.run(orders);

            answers[testCase] = cn.get();
        }
        for(int i = 0; i < 10; i++){
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
