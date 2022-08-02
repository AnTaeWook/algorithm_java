package dx.week7;

import java.util.HashMap;

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem5 {
    Node[] mailBox;
    int[] mailCount;
    HashMap<String, Integer> dictionary;
    int mailLimit;
    int dictionaryNumber;

    void init(int N, int K) {
        mailBox = new Node[N];
        mailCount = new int[N];
        dictionary = new HashMap<>();
        mailLimit = K;
        dictionaryNumber = 0;

        for (int i = 0; i < N; i++) {
            mailBox[i] = new Node("root");
        }
    }

    private String generateCode(char[] subject) {
        String[] words;
        StringBuilder sb = new StringBuilder();
        sb.append(".");
        words = new String(subject).split("\0")[0].split(" ");

        for (String word : words) {
            if (!dictionary.containsKey(word)) {
                dictionary.put(word, dictionaryNumber++);
            }
            sb.append(dictionary.get(word)).append(".");
        }
        return sb.toString();
    }

    void sendMail(char[] subject, int uID, int cnt, int[] rIDs) {
        String code = generateCode(subject);
        for (int i = 0; i < cnt; i++) {
            addMail(rIDs[i], code);
            mailCount[rIDs[i]]++;
            if (mailCount[rIDs[i]] > mailLimit) {
                tailCut(rIDs[i]);
                mailCount[rIDs[i]]--;
            }
        }
    }

    private void addMail(int rID, String code) {
        Node temp = mailBox[rID];
        while (true) {
            if (temp.next == null) {
                temp.next = new Node(code);
                return;
            }
            temp = temp.next;
        }
    }

    private void tailCut(int rID) {
        Node temp = mailBox[rID];
        temp.next = temp.next.next;
    }

    int getCount(int uID) {
        return mailCount[uID];
    }

    int deleteMail(int uID, char[] subject) {
        String code = generateCode(subject);
        Node temp = mailBox[uID];
        int count = 0;

        while (temp.next != null) {
            if (temp.next.data.equals(code)) {
                temp.next = temp.next.next;
                count++;
                continue;
            }
            temp = temp.next;
        }
        mailCount[uID] -= count;
        return count;
    }


    int searchMail(int uID, char[] text) {
        String code = generateCode(text);
        Node temp = mailBox[uID];
        int count = 0;

        while (temp != null) {
            if (temp.data.contains(code)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    public void show() {
        Node temp;
        for (int i = 0; i < 4; i++) {
            temp = mailBox[i];
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}
