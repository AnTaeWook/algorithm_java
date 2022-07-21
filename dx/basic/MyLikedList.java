package dx.basic;

import java.util.LinkedList;

public class MyLikedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(8);
        list.add(10);
        list.addFirst(1);

        list.remove();
        list.removeLast();

        for(Integer i : list){
            System.out.println(i);
        }
    }
}
