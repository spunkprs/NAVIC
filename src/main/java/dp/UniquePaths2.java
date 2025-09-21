package dp;

/**
 You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

 An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

 Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

 The testcases are generated so that the answer will be less than or equal to 2 * pow(10,9)

 Constraints:

1.) m == obstacleGrid.length
2.) n == obstacleGrid[i].length
3.) 1 <= m, n <= 100
4.) obstacleGrid[i][j] is 0 or 1.

 Source : LeetCode

 ----->Followed Bottom Up approach of DP to solve the problem<-------

 Time Complexity : O(M * N), where M && N are respective number of rows && columns in the table
 Space Complexity : O(M * N), where M && N are respective number of rows && columns in the table
 * */

public class UniquePaths2 {

    public static void main(String ar[]) {
        UniquePaths2 unit = new UniquePaths2();

        //int obstacleGrid[][] = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        //int obstacleGrid[][] = {{0}};
        int obstacleGrid[][] = {{0, 1, 0, 0}};
        System.out.println("Unique paths from source to destination is " + unit.uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int obstacleGridResult[][] = new int[obstacleGrid.length][obstacleGrid[0].length];

        if (obstacleGrid.length == 1 && obstacleGrid[0].length == 1) {
            if (obstacleGrid[0][0] == 0) {
                return 1;
            }
            return 0;
        } else {
            if (obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 0 && obstacleGrid[0][0] == 0) {
                for (int i = 0; i < obstacleGrid.length; i++) {
                    for (int j = 0; j < obstacleGrid[i].length; j++) {
                        if (obstacleGrid[i][j] == 0) {
                            int predecessorI = i - 1;
                            int predecessorJ = j - 1;

                            if (predecessorJ >= 0 && predecessorI < 0) {
                                if (obstacleGridResult[i][predecessorJ] == 0) {
                                    obstacleGridResult[i][j] = obstacleGridResult[i][predecessorJ] + 1;
                                } else {
                                    obstacleGridResult[i][j] = obstacleGridResult[i][predecessorJ];
                                }
                            }

                            if (predecessorI >= 0 && predecessorJ < 0) {
                                if (obstacleGridResult[predecessorI][j] == 0) {
                                    obstacleGridResult[i][j] = obstacleGridResult[predecessorI][j] + 1;
                                } else {
                                    obstacleGridResult[i][j] = obstacleGridResult[predecessorI][j];
                                }
                            }

                            if (predecessorI >= 0 && predecessorJ >= 0) {
                                if (obstacleGridResult[i][predecessorJ] < 0 && obstacleGridResult[predecessorI][j] < 0) {
                                    obstacleGridResult[i][j] = -1;
                                } else if ((obstacleGridResult[i][predecessorJ] >= 0 && obstacleGridResult[predecessorI][j] < 0)) {
                                    obstacleGridResult[i][j] = obstacleGridResult[i][predecessorJ];
                                } else if (obstacleGridResult[i][predecessorJ] < 0 && obstacleGridResult[predecessorI][j] >= 0) {
                                    obstacleGridResult[i][j] = obstacleGridResult[predecessorI][j];
                                } else {
                                    obstacleGridResult[i][j] = obstacleGridResult[i][predecessorJ] + obstacleGridResult[predecessorI][j];
                                }
                            }
                        } else {
                            obstacleGridResult[i][j] = -1;
                        }
                    }
                }
                return obstacleGridResult[obstacleGridResult.length - 1][obstacleGridResult[0].length - 1] < 0 ? 0 : obstacleGridResult[obstacleGridResult.length - 1][obstacleGridResult[0].length - 1];
            } else {
                return 0;
            }
        }
    }
}
