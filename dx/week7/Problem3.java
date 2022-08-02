package dx.week7;

import java.util.Scanner;

public class Problem3 {
    private static int[][] tempBoard = new int[2000][2000];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        int[] answers = new int[testCaseCount];

        for (int i = 0; i < testCaseCount; i++) {
            int patternH = scanner.nextInt();
            int patternW = scanner.nextInt();
            int originalN = scanner.nextInt();
            int originalM = scanner.nextInt();
            int[][] pattern = new int[patternH][patternW];
            int[][] original = new int[originalN][originalM];

            for (int j = 0; j < patternH; j++) {
                String data = scanner.next();
                for (int k = 0; k < patternW; k++) {
                    pattern[j][k] = data.charAt(k) == 'o' ? 1 : 0;
                }
            }
            for (int j = 0; j < originalN; j++) {
                String data = scanner.next();
                for (int k = 0; k < originalM; k++) {
                    original[j][k] = data.charAt(k) == 'o' ? 1 : 0;
                }
            }
            int patternCode = getPatternCode(pattern);

            for (int j = 0; j < originalN; j++) {
                tempBoard[j][0] = getHashRow(original, j, patternW);
            }
            rollingRow(original, patternW);
            int[][] finalOrigin = new int[originalN - patternH + 1][originalM - patternW + 1];
            for (int j = 0; j < originalM - patternW + 1; j++) {
                finalOrigin[0][j] = getHashCol(tempBoard, j, patternH);
            }

            rollingCol(finalOrigin, patternH);

            int matchCount = 0;
            for (int j = 0; j < finalOrigin.length; j++) {
                for (int k = 0; k < finalOrigin[0].length; k++) {
                    if (finalOrigin[j][k] == patternCode) {
                        matchCount += check(pattern, original, j, k) ? 1 : 0;
                    }
                }
            }
            answers[i] = matchCount;
        }

        for (int i = 0; i < testCaseCount; i++) {
            System.out.println("#" + (i + 1) + " " + answers[i]);
        }
    }

    private static int getPatternCode(int[][] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            tempBoard[i][0] = getHashRow(pattern, i, pattern[0].length);
        }
        return getHashCol(tempBoard, 0, pattern.length);
    }

    private static int getHashRow(int[][] board, int index, int len) {
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            hashCode += board[index][i] << i;
            hashCode %= 200003;
        }
        return hashCode;
    }

    private static int getHashCol(int[][] board, int index, int len) {
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            hashCode += board[i][index] << i;
            hashCode %= 200003;
        }
        return hashCode;
    }

    private static void rollingRow(int[][] original, int len) {
        for (int i = 0; i < original.length; i++) {
            for (int j = 1; j < original[0].length - len + 1; j++) {
                tempBoard[i][j] = tempBoard[i][j - 1] - original[i][j - 1];
                tempBoard[i][j] /= 2;
                tempBoard[i][j] += (1 << len - 1) * original[i][j + len - 1];
                tempBoard[i][j] %= 200003;
            }
        }
    }

    private static void rollingCol(int[][] finalOrigin, int len) {
        for (int i = 1; i < finalOrigin.length; i++) {
            for (int j = 0; j < finalOrigin[0].length; j++) {
                finalOrigin[i][j] = finalOrigin[i - 1][j] - tempBoard[i - 1][j];
                finalOrigin[i][j] /= 2;
                finalOrigin[i][j] += (1 << len - 1) * tempBoard[i + len - 1][j];
                finalOrigin[i][j] %= 200003;
            }
        }
    }

    private static boolean check(int[][] pattern, int[][] original, int x, int y) {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if (pattern[i][j] != original[i + x][j + y]) {
                    return false;
                }
            }
        }
        return true;
    }
}
