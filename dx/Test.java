package dx;

import java.util.*;
import java.util.stream.Collectors;


public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = {"1", "2", "3"};
        Arrays.sort(s, Comparator.reverseOrder());
        System.out.println(Arrays.toString(s));

        sc.close();
    }
}
