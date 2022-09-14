package skill;

public class ArrayCopy {
    public static void main(String[] args) {
        // 2차원 배열 복사
        int[][] arr1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr2 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(arr1[i], 0, arr2[i], 0, 3);
        }
    }
}
