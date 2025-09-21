package dp;

import java.util.PriorityQueue;

/**
 Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

 A falling path starts at any element in the first row and chooses the element in the next row that is either directly
 below or diagonally left/right.
 Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 Constraints:

1.) n == matrix.length == matrix[i].length
2.) 1 <= n <= 100
3.) -100 <= matrix[i][j] <= 100

Source : Leetcode

Time Complexity = O(N * N), where N being number of rows && columns in the matrix
Time Complexity = O(N * N), where N being number of rows && columns in the matrix

 ------> Used Bottom Up approach against DP to solve this problem <---------
 * */

public class MinimumFallingPathSum {

    public static void main(String ar[]) {
        MinimumFallingPathSum unit = new MinimumFallingPathSum();
        //int matrix[][] = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int matrix[][] = {{-19, 57}, {-40, -5}};

        System.out.print("Minimum falling sum for the provided matrix is " + unit.minFallingPathSum(matrix));

    }

    public int minFallingPathSum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 1 && matrix[0].length > 1) {
            for (int j = 0; j < matrix[0].length; j++) {
                min = matrix[0][j] < min ? matrix[0][j] : min;
            }
            return min;
        } else {
            int matrixResult[][] = processToComputeMinFallingSum(matrix);
            for (int j = 0; j < matrix[0].length; j++) {
                min = matrixResult[matrix.length - 1][j] < min ? matrixResult[matrix.length - 1][j] : min;
            }
            return min;
        }
    }

    private int[][] processToComputeMinFallingSum(int[][] matrix) {
        int matrixResult[][] = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            matrixResult[0][j] = matrix[0][j];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int predecessorI = i - 1;
                int predecessorJOne = j - 1;
                int predecessorJTwo = j + 1;

                PriorityQueue<Integer> minHeap = new PriorityQueue<>();
                minHeap.add(matrixResult[predecessorI][j]);

                if (predecessorJOne >= 0) {
                    minHeap.add(matrixResult[predecessorI][predecessorJOne]);
                }

                if (predecessorJTwo < matrix[0].length) {
                    minHeap.add(matrixResult[predecessorI][predecessorJTwo]);
                }

                matrixResult[i][j] = matrix[i][j] + minHeap.peek();
            }
        }
        return matrixResult;
    }
}
