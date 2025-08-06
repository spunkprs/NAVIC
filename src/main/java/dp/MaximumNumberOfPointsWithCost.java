package dp;

/**
 You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

 To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

 However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
 For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2)
 will subtract abs(c1 - c2) from your score.

 Return the maximum number of points you can achieve.

 abs(x) is defined as:

 1.) x for x >= 0.
 2.) -x for x < 0.

 Constraints:

 1.) m == points.length
 2.) n == points[r].length
 3.) 1 <= m, n <= 105
 4.) 1 <= m * n <= 105
 5.) 0 <= points[r][c] <= 105

 Source : LeetCode

 Time Complexity = O(m-1 * n * n)
 Space Complexity = O(m*n)
 * */

public class MaximumNumberOfPointsWithCost {

    public static void main(String ars[]) {
        MaximumNumberOfPointsWithCost unit = new MaximumNumberOfPointsWithCost();

        int[][] points = {{1,2,3},{1,5,1},{3,1,1}};

        System.out.print("Maximum number of points with cost for the above provided matrix is :: " + unit.maxPoints(points));
    }

    public long maxPoints(int[][] points) {
        if (points.length == 1) {
            return findMaximumPoint(points, 0);
        } else {
            long intermittentPoints[][] = new long[points.length][points[0].length];
            for (int j = 0; j < points[0].length; j++) {
                intermittentPoints[0][j] = points[0][j];
            }

            for (int i = 1; i < points.length; i++) {
                for (int j = 0; j < points[0].length; j++) {
                    prepareValueAtIndex(intermittentPoints, i, j, points);
                }
            }
            return findMaximumPoint(intermittentPoints, intermittentPoints.length - 1);
        }
    }

    private void prepareValueAtIndex(long[][] intermittentPoints, int i, int j, int points[][]) {
        long maxNum = -1;
        long computedValue = 0;
        for (int k = 0; k < intermittentPoints[0].length; k++) {
            computedValue = intermittentPoints[i - 1][k] + points[i][j] - Math.abs(j - k);
            maxNum = computedValue > maxNum ? computedValue : maxNum;
        }
        intermittentPoints[i][j] = maxNum;
    }

    private long findMaximumPoint(int[][] points, int i) {
        int maxNum = -1;
        for (int j = 0; j < points[0].length; j++) {
            maxNum = points[i][j] > maxNum ? points[i][j] : maxNum;
        }
        return maxNum;
    }

    private long findMaximumPoint(long[][] points, int i) {
        long maxNum = -1;
        for (int j = 0; j < points[0].length; j++) {
            maxNum = points[i][j] > maxNum ? points[i][j] : maxNum;
        }
        return maxNum;
    }
}
