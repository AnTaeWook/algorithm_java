package dx.week3;

import java.util.Arrays;
import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] answers = new String[10];
        int[] dataset;
        int totalCountOfNode;
        int nodeIndex1, nodeIndex2;
        int leastCommonAncestor;
        BSTWithParent bst = new BSTWithParent();

        scanner.nextLine();
        for(int i = 0; i < 10; i++){
            dataset = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            totalCountOfNode = dataset[0];
            nodeIndex1 = dataset[2];
            nodeIndex2 = dataset[3];
            bst.initWithIndex(totalCountOfNode);
            bst.add(Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            leastCommonAncestor = bst.getLeastCommonAncestor(nodeIndex1, nodeIndex2);
            answers[i] = leastCommonAncestor + " " + bst.getSubtreeSize(leastCommonAncestor);
        }

        for(int i = 0; i < 10; i++){
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
