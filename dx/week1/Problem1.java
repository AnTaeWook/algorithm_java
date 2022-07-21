package dx.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        ArrayList<Integer> answers = new ArrayList<>();

        for(int i = 0; i < caseCount; i++){
            int num = scanner.nextInt(), multiplier = 0, checksum = 0;
            while (checksum < 1023){
                int temp = ++multiplier*num;
                while(temp > 0) {
                    checksum |= 1 << temp % 10;
                    temp /= 10;
                }
            }
            answers.add(num*multiplier);
        }

        for(int i = 0; i < answers.size(); i++){
            System.out.println("#" + (i + 1) + " " + answers.get(i));
        }
        scanner.close();
    }
}
