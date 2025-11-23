package leetcode75;

public class LongestCommonSubsequence {

    public static void main(String ar[]) {
        LongestCommonSubsequence unit = new LongestCommonSubsequence();
        String textOne = "abc";
        String textTwo = "abc";

        System.out.println("Length of longest common subsequence is :: " + unit.longestCommonSubsequence(textOne, textTwo));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char arrOne[] = text1.toCharArray();
        char arrTwo[] = text2.toCharArray();

        if (text1.length() == text2.length() && text1.length() == 1) {
            if (arrOne[0] == arrTwo[0]) {
                return 1;
            }
            return 0;
        } else if (text1.length() == 1 && text2.length() > 1) {
            return text2.contains(text1) ? 1 : 0;
        } else if (text2.length() == 1 && text1.length() > 1) {
            return text1.contains(text2) ? 1 : 0;
        } else {
            int lenOne = arrOne.length;
            int lenTwo = arrTwo.length;

            if (lenOne >= lenTwo) {
                return processToFindLCS(arrOne, arrTwo);
            } else {
                return processToFindLCS(arrTwo, arrOne);
            }
        }
    }

    private int processToFindLCS(char arrOne[], char arrTwo[]) {
        int matrix[][] = new int[arrTwo.length][arrOne.length];

        for (int i = 0; i < arrTwo.length; i++) {
            for (int j = 0; j < arrOne.length; j++) {
                if (arrTwo[i] == arrOne[j]) {
                    if (i - 1 < 0 || j - 1 < 0) {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = 1 + matrix[i - 1][j - 1];
                    }
                } else {
                    int numOne = fetchFirstValue(matrix, i, j);
                    int numTwo = fetchSecondValue(matrix, i, j);
                    matrix[i][j] = Math.max(numOne, numTwo);
                }
            }
        }
        return matrix[arrTwo.length - 1][arrOne.length - 1];
    }

    private int fetchSecondValue(int[][] matrix, int row, int column) {
        if (column - 1 >= 0) {
            return matrix[row][column - 1];
        }
        return 0;
    }

    private int fetchFirstValue(int[][] matrix, int row, int column) {
        if (row - 1 >= 0) {
            return matrix[row - 1][column];
        }
        return 0;
    }
}
