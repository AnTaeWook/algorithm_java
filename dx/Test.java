package dx;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>(0);
        deque.offer(52);
        System.out.println(deque.poll());
        sc.close();
    }
}
