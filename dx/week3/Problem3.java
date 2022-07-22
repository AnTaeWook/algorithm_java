package dx.week3;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] answers = new int[10];
        int totalCountOfNode;
        BST bst = new BST();

        for(int i = 0; i < 10; i++){
            totalCountOfNode = scanner.nextInt();
            bst.initWithIndex(totalCountOfNode);
            scanner.nextLine();
            for(int j = 0; j < totalCountOfNode; j++){
                bst.add(scanner.nextLine().split(" "));
            }
            answers[i] = bst.getResult();
        }

        for(int i = 0; i < 10; i++){
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
