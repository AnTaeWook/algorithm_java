package skill;

import java.util.Arrays;

public class IntegerArray {

    public static void main(String[] args) {
        // Integer 배열 -> int 배열
        Integer[] arr = {1, 2, 3, 4, 5};

        int[] arr2 = Arrays.stream(arr).mapToInt(i -> i).toArray();
    }
}

