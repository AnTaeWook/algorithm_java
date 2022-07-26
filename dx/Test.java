package dx;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] path = new char[2000];
        for (int i = 0; i < 10; i++) {
            path[i] = 'z';
        }
        String s = "";
        for (int i = 0; i < path.length; i++) {
            if (path[i] == '\0') {
                break;
            }
            s += String.valueOf(path[i]);
        }
        System.out.println(s + "Y");
        sc.close();
    }
}
