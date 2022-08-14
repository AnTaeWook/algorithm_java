package dx.week11;

import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        String[] answers = new String[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int order = scanner.nextInt();
            String word = scanner.next();
            Trie trie = new Trie();
            for (int j = 0; j < word.length(); j++) {
                trie.insert(word.substring(j));
            }
            answers[i] = trie.findInOrder(order);
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
