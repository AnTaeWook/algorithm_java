package dx.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Boolean> answers = new ArrayList<>();

        for(int i = 0; i < caseCount; i++){
            Boolean isOn = true;
            String[] nums = scanner.nextLine().split(" ");
            for(int j = 0; j < Integer.parseInt(nums[0]); j++){
                if((Integer.parseInt(nums[1]) & 1 << j) == 0){
                    isOn = false;
                    break;
                }
            }
            answers.add(isOn);
        }

        for(int i = 0; i < answers.size(); i++){
            String state = answers.get(i) ? "ON" : "OFF";
            System.out.println("#" + (i + 1) + " " + state);
        }
    }
}
