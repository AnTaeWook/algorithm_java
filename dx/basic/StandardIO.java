package dx.basic;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class StandardIO {
    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new java.io.FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int t, r, c;
        char arr[][] = new char[100][100];
        t = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        for(int i = 0; i < r; i++){
            String str = sc.next();
            for(int j = 0; j < c; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        System.out.printf("%d\n%d\n%d\n", t, r, c);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.printf("%c", arr[i][j]);
            }
            System.out.println();
        }
        sc.close();
    }
}
