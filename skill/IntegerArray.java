package skill;

import java.util.*;

public class IntegerArray {

    public static void main(String[] args) {
        // Integer 배열 -> int 배열
        Integer[] arr = {1, 2, 3, 4, 5};
        int[] arr2 = Arrays.stream(arr).mapToInt(i -> i).toArray();

        // int 배열 -> Integer 배열
        Integer[] arr3 = Arrays.stream(arr2).boxed().toArray(Integer[]::new);

        // Integer[] 를 Collection 으로 변환
        ArrayDeque<Integer> deque = new ArrayDeque<>(Arrays.asList(arr));

        // String[]을 int[]로 변환
        String[] strArr = {"123", "12", "5"};
        int[] numArr = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();

        // Collection 자료구조의 내부 합 구하기
        int sum = deque.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // ArrayList 합치기
        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));
        arrayList2.addAll(arrayList1);
        System.out.println(arrayList2);
    }
}

