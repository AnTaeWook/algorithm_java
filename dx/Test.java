package dx;

import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next().toLowerCase();
        TreeMap<String, Integer> characterCount = new TreeMap<>();
        int maximum = -1;

        for (int i = 0; i < string.length(); i++) {
            characterCount.put(string.substring(i, i + 1), characterCount.getOrDefault(string.substring(i, i + 1), 0) + 1);
            maximum = Math.max(characterCount.get(string.substring(i, i + 1)), maximum);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String appeared : characterCount.keySet()) {
            if (characterCount.get(appeared) == maximum) {
                stringBuilder.append(appeared);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
