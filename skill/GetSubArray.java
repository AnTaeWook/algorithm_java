package skill;

import java.util.Arrays;

public class GetSubArray {
    public static void main(String[] args) {

        // 배열에서 필요한 부분만 슬라이싱하기
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] subArr = Arrays.copyOfRange(arr, 1, 5);
        System.out.println(Arrays.toString(subArr));
    }
}
