package dx.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Integer> answers = new ArrayList<>();

        int[][] dp;
        for(int i = 0; i < caseCount; i++){
            String responsibles = scanner.next();
            dp = new int[responsibles.length()][16];
            getOnFirstDay(responsibles, dp);
            for(int j = 1; j < responsibles.length(); j++){
                getOnOtherDays(responsibles, dp, j);
            }
            answers.add(getFromDp(dp));
        }

        for(int i = 0; i < answers.size(); i++){
            System.out.println("#" + (i + 1) + " " + answers.get(i));
        }
    }

    public static void getOnFirstDay(String responsibles, int[][] dp){
        int responsible = 1 << (responsibles.charAt(0) - 'A');
        for(int i = 1; i < 16; i++){
            dp[0][i] = (i & responsible) != 0 && (i & 1) != 0 ? 1 : 0;
        }
    }

    public static void getOnOtherDays(String responsibles, int[][] dp, int day){
        int responsible = 1 << (responsibles.charAt(day) - 'A');
        for(int i = 1; i < 16; i++){
            if(dp[day - 1][i] != 0){
                for(int j = 1; j < 16; j++){
                    if((j & i) != 0 && (j & responsible) != 0) {
                        dp[day][j] += dp[day - 1][i];
                        dp[day][j] %= 1000000007;
                    }
                }
            }
        }
    }

    public static int getFromDp(int[][] dp){
        int count = 0;
        for(int i = 1; i < 16; i++){
            count += dp[dp.length - 1][i];
            count %= 1000000007;
        }
        return count;
    }
}
