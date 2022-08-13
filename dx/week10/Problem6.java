package dx.week10;

import java.util.Scanner;

public class Problem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int memoryCount = scanner.nextInt();
            int initDataBlockCount = scanner.nextInt();
            int[] memory = new int[memoryCount];
            int[] initDataBlock = new int[initDataBlockCount];

            int maxMemory = 0;
            for (int j = 0; j < memoryCount; j++) {
                memory[j] = scanner.nextInt();
                maxMemory = Math.max(maxMemory, memory[j]);
            }
            for (int j = 0; j < initDataBlockCount; j++) {
                initDataBlock[j] = scanner.nextInt();
            }

            int start = 0;
            int end = maxMemory + 1;
            while (start != end) {
                int mid = (start + end)/2;
                int blockIndex = 0, validMemory = 0;
                boolean isPossible = false;
                for (int j = 0; j < memoryCount; j++) {
                    if (mid >= memory[j]) {
                        validMemory += 1;
                        if (validMemory == initDataBlock[blockIndex]) {
                            blockIndex += 1;
                            if (blockIndex == initDataBlockCount) {
                                isPossible = true;
                                break;
                            }
                            validMemory = 0;
                        }
                    } else {
                        validMemory = 0;
                    }
                }
                if (isPossible) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            answers[i] = start;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }
}
