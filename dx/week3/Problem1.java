package dx.week3;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] answers = new String[10];
        int totalCountOfNode;
        BST bst = new BST();

        for(int i = 0; i < 10; i++){
            bst.init();
            totalCountOfNode = scanner.nextInt();
            scanner.nextLine();
            for(int j = 0; j < totalCountOfNode; j++){
                bst.addForComplete(scanner.nextLine().split(" ")[1]);
            }
            answers[i] = bst.getStringWithInOrder();
        }

        for(int i = 0; i < 10; i++){
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
