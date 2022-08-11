package dx.week9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem1 {

    static Comparator<String> comparator = (o1, o2) -> {
        if (o1.length() == o2.length()) {
            return o1.compareTo(o2);
        }
        return o1.length() - o2.length();
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; i++) {
            int nameCount = scanner.nextInt();
            String[] nameList = new String[nameCount];
            for (int j = 0; j < nameCount; j++) {
                nameList[j] = scanner.next();
            }
            Arrays.sort(nameList, comparator);
            String memo = "";
            System.out.println("#" + (i + 1));
            for (int j = 0; j < nameCount; j++) {
                if (nameList[j].equals(memo)) {
                    continue;
                }
                System.out.println(nameList[j]);
                memo = nameList[j];
            }
        }
    }
}
