package dx;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {

        TreeSet<Integer> t = new TreeSet<>();
        t.add(5);
        t.add(4);
        t.add(3);
        System.out.println(t.higher(3));
    }
}
